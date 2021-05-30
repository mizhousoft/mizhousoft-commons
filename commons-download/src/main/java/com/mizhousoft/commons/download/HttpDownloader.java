package com.mizhousoft.commons.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.commons.thread.FixedThreadPool;

/**
 * 文件下载器
 *
 * @version
 */
public class HttpDownloader implements Downloader, ProgressListener
{
	private static final Logger LOG = LoggerFactory.getLogger(HttpDownloader.class);

	/**
	 * 下载标识
	 */
	protected final String mark;

	/**
	 * 下载地址
	 */
	protected final String fileUrl;

	/**
	 * 本地文件路径
	 */
	protected final String localFilePath;

	/**
	 * 文件大小
	 */
	protected long fileSize;

	/**
	 * 开始时间
	 */
	protected Date startTime;

	/**
	 * 结束时间
	 */
	protected Date endTime;

	/**
	 * 线程数，默认是计算出来
	 */
	protected int threadNum;

	/**
	 * 下载线程
	 */
	protected List<DownloadThread> downloadThreads;

	/**
	 * 下载线程计数器
	 */
	protected CountDownLatch countDownLatch;

	/**
	 * 是否显示下载状态信息
	 */
	protected boolean verbose;

	/**
	 * 重试次数
	 */
	protected int retry;

	/**
	 * 重试延迟时间，单位是毫秒
	 */
	protected long retryDelayTime;

	/**
	 * 连接超时时间，单位是毫秒
	 */
	protected int connectTimeout;

	/**
	 * 读取超时时间，单位是毫秒
	 */
	protected int readTimeout;

	/**
	 * 下载异常
	 */
	protected DownloadException cause;

	/**
	 * 构造函数
	 *
	 * @param mark
	 * @param fileUrl
	 * @param localFilePath
	 */
	public HttpDownloader(String mark, String fileUrl, String localFilePath)
	{
		this.mark = mark;
		this.fileUrl = fileUrl;
		this.localFilePath = localFilePath;

		this.threadNum = 0;
		this.verbose = false;
		this.retry = DownloadConstants.RETRY_NUMBER;
		this.retryDelayTime = DownloadConstants.RETRY_DELAY_TIME;
		this.connectTimeout = DownloadConstants.CONNECT_TIMEOUT;
		this.readTimeout = DownloadConstants.READ_TIMEOUT;

	}

	/**
	 * 开始下载
	 * 
	 * @throws DownloadException
	 */
	@Override
	public void start() throws DownloadException
	{
		// 获取文件大小
		getDownloadFileSize();

		// 是否要下载文件
		if (!isNeedDownloadFile())
		{
			LOG.info("File download successfully.");
			return;
		}

		// 准备下载文件
		prepareDownloadFile();

		// 创建下载线程
		ExecutorService executorService = startupDownloadFile();

		// 等待下载完成
		boolean succeed = await(executorService);

		// 是否下载成功
		if (succeed)
		{
			rename();
			deleteStatusFile();

			// 显示下载统计信息
			printDownloadStatistics();
		}
		else
		{
			// 输出下载失败
			printDownloadError();
		}
	}

	/**
	 * 获取文件大小
	 * 
	 * @throws DownloadException
	 */
	private void getDownloadFileSize() throws DownloadException
	{
		HttpURLConnection conn = null;

		try
		{
			conn = openHttpURLConnection();
			conn.setConnectTimeout(30 * 1000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != HttpServletResponse.SC_OK)
			{
				throw new DownloadException("Get file size failed.");
			}

			int length = conn.getContentLength();
			if (length < 1)
			{
				throw new DownloadException("Get file size failed.");
			}

			this.fileSize = length;
		}
		catch (IOException e)
		{
			throw new DownloadException("Get file size failed.", e);
		}
		finally
		{
			if (null != conn)
			{
				conn.disconnect();
			}
		}
	}

	/**
	 * 打开下载连接
	 * 
	 * @return
	 * @throws DownloadException
	 */
	public HttpURLConnection openHttpURLConnection() throws DownloadException
	{
		try
		{
			URL url = new URL(fileUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			return conn;
		}
		catch (IOException e)
		{
			throw new DownloadException("Open download connection failed.", e);
		}
	}

	/**
	 * 是否要下载文件
	 * 
	 * @return
	 * @throws DownloadException
	 */
	private boolean isNeedDownloadFile() throws DownloadException
	{
		File file = new File(localFilePath);
		if (file.exists())
		{
			long length = file.length();
			if (fileSize != length)
			{
				throw new DownloadException(file.getAbsolutePath() + " does already exist.");
			}
			else
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * 准备下载文件
	 * 
	 * @throws DownloadException
	 */
	private void prepareDownloadFile() throws DownloadException
	{
		// 创建文件目录
		mkdirFileDirectory();

		// 计算下载线程数
		calcDownloadThreadNumber();

		// 创建下载临时文件
		touchDownloadTmpFile();
	}

	/**
	 * 创建文件目录
	 * 
	 * @throws DownloadException
	 */
	private void mkdirFileDirectory() throws DownloadException
	{
		File file = new File(localFilePath);

		// 创建文件目录
		File parentFile = file.getParentFile();
		if (!parentFile.exists())
		{
			boolean ok = parentFile.mkdirs();
			if (!ok)
			{
				throw new DownloadException("Create directory " + file.getAbsolutePath() + " failed.");
			}
		}
	}

	/**
	 * 计算下载线程数
	 * 
	 */
	private void calcDownloadThreadNumber()
	{
		if (threadNum > 0)
		{
			return;
		}

		int number = 1;

		// 10M开启1个下载线程
		if (fileSize < 10 * 1024 * 1024)
		{
			number = 1;
		}
		// 50M开启2个下载线程
		else if (fileSize < 50 * 1024 * 1024)
		{
			number = 2;
		}
		// 100M开启4个下载线程
		else if (fileSize < 100 * 1024 * 1024)
		{
			number = 4;
		}
		// 200M开启10个下载线程
		else if (fileSize < 200 * 1024 * 1024)
		{
			number = 10;
		}
		// 其他开启20个下载线程
		else
		{
			number = 20;
		}

		// 不能大于CPU数量
		int cpuNum = Runtime.getRuntime().availableProcessors();
		if (number > cpuNum)
		{
			number = cpuNum;
		}

		this.threadNum = number;
	}

	/**
	 * 创建下载临时文件
	 * 
	 * @throws DownloadException
	 */
	private void touchDownloadTmpFile() throws DownloadException
	{
		File tmpFile = getLocalTmpFile();
		if (tmpFile.exists())
		{
			File statusFile = getStatusFile();
			if (!statusFile.exists())
			{
				boolean ok = tmpFile.delete();
				if (!ok)
				{
					throw new DownloadException("Force to delete download temp file failed.");
				}
			}
			else
			{
				return;
			}
		}

		RandomAccessFile randomAccessFile = null;

		try
		{
			tmpFile = getLocalTmpFile();
			randomAccessFile = new RandomAccessFile(tmpFile, "rw");
			randomAccessFile.setLength(fileSize);
		}
		catch (IOException e)
		{
			throw new DownloadException("Touch download temp file failed.", e);
		}
		finally
		{
			DownloadUtils.closeFile(randomAccessFile);
		}
	}

	/**
	 * 启动下载文件
	 * 
	 */
	private ExecutorService startupDownloadFile()
	{
		downloadThreads = new ArrayList<DownloadThread>(threadNum);
		DownloadThread downloadThread = null;

		// 计算线程状态数据
		List<ThreadStatusData> statusDatas = calcThreadStatusDatas();
		for (ThreadStatusData statusData : statusDatas)
		{
			downloadThread = createDownloadThread(statusData);
			downloadThreads.add(downloadThread);
		}

		countDownLatch = new CountDownLatch(threadNum);
		startTime = new Date();

		LOG.info("Start to download file, file url is " + fileUrl + '.');

		ExecutorService executorService = FixedThreadPool.newThreadPool(threadNum, "download-" + mark);
		for (DownloadThread thread : downloadThreads)
		{
			executorService.execute(thread);
		}

		return executorService;
	}

	/**
	 * 计算线程状态数据
	 * 
	 * @return
	 */
	private List<ThreadStatusData> calcThreadStatusDatas()
	{
		List<ThreadStatusData> threadStatusDatas = new ArrayList<ThreadStatusData>(threadNum);

		// 每个下载线程大小
		long bytesPerThread = fileSize / threadNum;

		long offset = 0;
		int i = 0;
		ThreadStatusData statusData = null;

		for (; i < (threadNum - 1); i++)
		{
			statusData = new ThreadStatusData(offset, offset + bytesPerThread, 0);
			threadStatusDatas.add(statusData);

			offset += bytesPerThread;
		}

		statusData = new ThreadStatusData(offset, fileSize, 0);
		threadStatusDatas.add(statusData);

		List<ThreadStatusData> fileStatusDatas = readFileStatusDatas();

		boolean match = isAllThreadStatusDatasMatch(threadStatusDatas, fileStatusDatas);
		if (match)
		{
			threadStatusDatas = fileStatusDatas;
		}

		return threadStatusDatas;
	}

	/**
	 * 读取文件状态数据
	 * 读取不到数据直接返回，保证不能影响到下载业务
	 * 
	 * @return
	 */
	private List<ThreadStatusData> readFileStatusDatas()
	{
		FileInputStream istream = null;
		DataInputStream dataStream = null;

		try
		{
			File statusFile = getStatusFile();
			if (!statusFile.exists())
			{
				return Collections.<ThreadStatusData> emptyList();
			}

			istream = new FileInputStream(statusFile);
			dataStream = new DataInputStream(istream);

			int size = dataStream.readInt();
			List<ThreadStatusData> statusDatas = new ArrayList<ThreadStatusData>(size);

			ThreadStatusData statusData = null;
			long start = 0;
			long end = 0;
			long read = 0;

			for (int i = 0; i < size; i++)
			{
				start = dataStream.readLong();
				read = dataStream.readLong();
				end = dataStream.readLong();

				statusData = new ThreadStatusData(start, end, read);
				statusDatas.add(statusData);
			}

			return statusDatas;
		}
		catch (Exception e)
		{
			LOG.warn("Read file status data failed.", e);
			return Collections.<ThreadStatusData> emptyList();
		}
		finally
		{
			DownloadUtils.closeStream(istream);
			DownloadUtils.closeStream(dataStream);
		}
	}

	/**
	 * 判断线程状态数据是否匹配
	 * 
	 * @param threadStatusDatas
	 * @param fileStatusDatas
	 * @return
	 */
	private boolean isAllThreadStatusDatasMatch(List<ThreadStatusData> threadStatusDatas,
	        List<ThreadStatusData> fileStatusDatas)
	{
		if (threadStatusDatas.size() != fileStatusDatas.size())
		{
			return false;
		}

		boolean match = false;

		for (ThreadStatusData statusData : threadStatusDatas)
		{
			match = false;

			for (ThreadStatusData fsd : fileStatusDatas)
			{
				if (statusData.getStart() == fsd.getStart() && statusData.getEnd() == fsd.getEnd())
				{
					match = true;
					break;
				}
			}

			if (!match)
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * 创建线程
	 * 
	 * @param statusData
	 * @return
	 */
	private DownloadThread createDownloadThread(ThreadStatusData statusData)
	{
		DownloadThread downloadThread = new DownloadThread(statusData, this);
		downloadThread.setRetry(retry);
		downloadThread.setRetryDelayTime(retryDelayTime);
		downloadThread.setConnectTimeout(connectTimeout);
		downloadThread.setReadTimeout(readTimeout);

		return downloadThread;
	}

	/**
	 * 等待下载完成
	 * 
	 * @param executorService
	 * @return
	 * @throws DownloadException
	 */
	private boolean await(ExecutorService executorService) throws DownloadException
	{
		try
		{
			int count = 0;

			while (true)
			{
				TimeUnit.SECONDS.sleep(5);

				// 输出下载状态
				if (verbose)
				{
					printDownloadStatus();
				}

				// 每隔10秒写一次状态数据
				if (count == 2)
				{
					// 重置
					count = 0;

					writeFileStatusDatas();
				}

				count = count + 1;

				// 下载完成
				if (0 == countDownLatch.getCount())
				{
					break;
				}
			}

			writeFileStatusDatas();

			boolean succeed = isDownloadSucceed();
			return succeed;
		}
		catch (InterruptedException e)
		{
			throw new DownloadException("Download file timeout.", e);
		}
		finally
		{
			endTime = new Date();
			executorService.shutdownNow();
		}
	}

	/**
	 * 输出下载状态
	 */
	private void printDownloadStatus()
	{
		long progress = 0;
		for (DownloadThread downloadThread : downloadThreads)
		{
			progress = progress + downloadThread.getStatusData().getRead();
		}

		long pct = progress * 100 / fileSize;

		StringBuilder buffer = new StringBuilder();
		buffer.append(pct).append("% ").append(progress).append("/").append(fileSize);

		LOG.info(buffer.toString());
	}

	/**
	 * 是否下载成功
	 * 
	 * @return
	 */
	private boolean isDownloadSucceed()
	{
		for (DownloadThread downloadThread : downloadThreads)
		{
			if (!downloadThread.isFinish())
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * 输出下载统计信息
	 */
	private void printDownloadStatistics()
	{
		long offset = endTime.getTime() - startTime.getTime();
		long secs = offset / 1000;
		long rate = fileSize / secs;

		StringBuilder buffer = new StringBuilder();
		buffer.append("Downloaded ").append(fileSize).append(" bytes in ").append(secs).append(" seconds (")
		        .append(rate).append(" bytes/s)");

		LOG.info(buffer.toString());
	}

	/**
	 * 写文件状态数据
	 * 写入文件失败直接返回，保证不影响到下载业务
	 * 
	 */
	private void writeFileStatusDatas()
	{
		List<ThreadStatusData> statusDatas = new ArrayList<ThreadStatusData>(downloadThreads.size());
		for (DownloadThread downloadThread : downloadThreads)
		{
			statusDatas.add(downloadThread.getStatusData());
		}

		File statusFile = getStatusFile();
		if (!statusFile.exists())
		{
			boolean ok = false;

			try
			{
				ok = statusFile.createNewFile();
			}
			catch (IOException e)
			{
				ok = false;
			}

			if (!ok)
			{
				LOG.warn("Create status data file failed, data does not write file.");
				return;
			}
		}

		FileOutputStream fileStream = null;
		DataOutputStream dataStream = null;

		try
		{
			fileStream = new FileOutputStream(statusFile);
			dataStream = new DataOutputStream(fileStream);

			dataStream.writeInt(statusDatas.size());
			for (ThreadStatusData statusData : statusDatas)
			{
				dataStream.writeLong(statusData.getStart());
				dataStream.writeLong(statusData.getRead());
				dataStream.writeLong(statusData.getEnd());
			}
		}
		catch (Exception e)
		{
			LOG.warn("Write file status data failed.", e);

			boolean ok = statusFile.delete();
			if (!ok)
			{
				LOG.error("Delete status data file failed.");
			}
		}
		finally
		{
			DownloadUtils.closeStream(fileStream);
			DownloadUtils.closeStream(dataStream);
		}
	}

	/**
	 * 输出下载失败
	 */
	private void printDownloadError()
	{
		LOG.error("Download file failed.", cause);
	}

	/**
	 * 文件重命名
	 * 
	 * @throws DownloadException
	 */
	private void rename() throws DownloadException
	{
		File srcFile = getLocalTmpFile();
		String srcPath = srcFile.getAbsolutePath();
		if (!srcFile.exists())
		{
			throw new DownloadException("Source file [" + srcPath + "] does not exist.");
		}

		File destFile = new File(localFilePath);
		String destPath = destFile.getAbsolutePath();
		if (destFile.exists() && !destFile.delete())
		{
			throw new DownloadException("Destination file [" + destPath + "] already exists and could not be deleted.");
		}

		boolean ok = srcFile.renameTo(destFile);
		if (!ok)
		{
			LOG.warn(srcPath + " rename to " + destPath + " failed.");

			BufferedInputStream istream = null;
			BufferedOutputStream ostream = null;

			try
			{
				istream = new BufferedInputStream(new FileInputStream(destFile));
				ostream = new BufferedOutputStream(new FileOutputStream(srcFile));

				byte[] buffer = new byte[1024 * 4];

				int n = istream.read(buffer);
				while (-1 != n)
				{
					ostream.write(buffer, 0, n);
					n = istream.read(buffer);
				}
			}
			catch (FileNotFoundException e)
			{
				throw new DownloadException("Source file [" + srcFile.getAbsolutePath() + "] does not exist.", e);
			}
			catch (IOException e)
			{
				throw new DownloadException("Rename file failed.", e);
			}
			finally
			{
				DownloadUtils.closeStream(istream);
				DownloadUtils.closeStream(ostream);
			}
		}
	}

	/**
	 * 获取本地临时文件
	 * 
	 * @return
	 */
	public File getLocalTmpFile()
	{
		String tmpFilePath = localFilePath + ".tmp";
		File tmpFile = new File(tmpFilePath);
		return tmpFile;
	}

	/**
	 * 获取下载状态文件
	 * 
	 * @return
	 */
	private File getStatusFile()
	{
		String tmpFilePath = localFilePath + ".status";
		File tmpFile = new File(tmpFilePath);
		return tmpFile;
	}

	/**
	 * 删除状态文件
	 */
	private void deleteStatusFile()
	{
		File tmpFile = getStatusFile();
		if (tmpFile.exists())
		{
			boolean ok = tmpFile.delete();
			if (!ok)
			{
				LOG.warn("Delete file[" + tmpFile.getAbsolutePath() + "] failed.");
			}
		}
	}

	/**
	 * 下载线程计数器减-
	 */
	public void countDown()
	{
		countDownLatch.countDown();
	}

	/**
	 * 下载失败
	 * 
	 * @param cause
	 */
	@Override
	public void error(DownloadException cause)
	{
		this.cause = cause;
	}

	/**
	 * 设置threadNum
	 * 
	 * @param threadNum
	 */
	public void setThreadNum(int threadNum)
	{
		this.threadNum = threadNum;
	}

	/**
	 * 设置verbose
	 * 
	 * @param verbose
	 */
	public void setVerbose(boolean verbose)
	{
		this.verbose = verbose;
	}

	/**
	 * 设置retry
	 * 
	 * @param retry
	 */
	public void setRetry(int retry)
	{
		this.retry = retry;
	}

	/**
	 * 设置retryDelayTime
	 * 
	 * @param retryDelayTime
	 */
	public void setRetryDelayTime(long retryDelayTime)
	{
		this.retryDelayTime = retryDelayTime;
	}

	/**
	 * 设置connectTimeout
	 * 
	 * @param connectTimeout
	 */
	public void setConnectTimeout(int connectTimeout)
	{
		this.connectTimeout = connectTimeout;
	}

	/**
	 * 设置readTimeout
	 * 
	 * @param readTimeout
	 */
	public void setReadTimeout(int readTimeout)
	{
		this.readTimeout = readTimeout;
	}
}
