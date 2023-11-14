package com.mizhousoft.commons.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletResponse;

/**
 * 下载线程
 *
 * @version
 */
class DownloadThread implements Runnable
{
	private static final Logger LOG = LoggerFactory.getLogger(DownloadThread.class);

	// 文件下载器
	private final HttpDownloader downloader;

	// 下载开始偏移
	private final ThreadStatusData statusData;

	// 连接超时时间，单位是毫秒
	private int connectTimeout;

	// 读取超时时间，单位是毫秒
	private int readTimeout;

	// 重试次数
	private int retry;

	// 重试延迟时间，单位是毫秒
	private long retryDelayTime;

	/**
	 * 构造函数
	 *
	 * @param statusData
	 * @param downloader
	 */
	public DownloadThread(ThreadStatusData statusData, HttpDownloader downloader)
	{
		this.statusData = statusData;
		this.downloader = downloader;
	}

	/**
	 * 执行线程
	 */
	@Override
	public void run()
	{
		try
		{
			execute();
		}
		catch (Throwable e)
		{
			LOG.error("Download part file failed.", e);
		}
		finally
		{
			downloader.countDown();
		}
	}

	/**
	 * 执行下载
	 * 
	 */
	private void execute()
	{
		// 下载是否成功
		boolean succeed = false;
		DownloadException cause = null;

		while (retry > 0)
		{
			if (isFinish())
			{
				succeed = true;
				break;
			}

			try
			{
				downownload();
				succeed = true;
				break;
			}
			catch (DownloadException e)
			{
				cause = e;
			}

			try
			{
				TimeUnit.MICROSECONDS.sleep(retryDelayTime);
			}
			catch (InterruptedException e)
			{
				LOG.error("Force to stop download file thread.");
				break;
			}

			retry = retry - 1;
		}

		if (succeed)
		{
			cause = null;
		}

		if (null != cause)
		{
			downloader.error(cause);
		}
	}

	/**
	 * 下载
	 * 
	 * @throws DownloadException
	 */
	private void downownload() throws DownloadException
	{
		RandomAccessFile raf = null;
		InputStream istream = null;
		HttpURLConnection conn = null;

		try
		{
			raf = new RandomAccessFile(downloader.getLocalTmpFile(), "rw");
			long startPos = statusData.getStartPosition();
			raf.seek(startPos);

			conn = downloader.openHttpURLConnection();

			// 获取下载输入流
			istream = getDownloadInputStream(conn);

			// 5 * 1024
			byte[] buffer = new byte[5120];
			int len = istream.read(buffer);
			while (len != -1)
			{
				raf.write(buffer, 0, len);
				statusData.incrementRead(len);

				len = istream.read(buffer);
			}
		}
		catch (DownloadException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new DownloadException("Download file failed.", e);
		}
		finally
		{
			DownloadUtils.closeStream(istream);
			DownloadUtils.closeFile(raf);

			if (null != conn)
			{
				conn.disconnect();
			}
		}
	}

	/**
	 * 获取下载输入流
	 * 
	 * @param conn
	 * @return
	 * @throws DownloadException
	 */
	private InputStream getDownloadInputStream(HttpURLConnection conn) throws DownloadException
	{
		try
		{
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);

			long startPos = statusData.getStartPosition();
			long endPos = statusData.getEnd() - 1;

			String property = "bytes=" + startPos + "-" + endPos;
			conn.setRequestProperty("Range", property);

			int code = conn.getResponseCode();
			if (code != HttpServletResponse.SC_OK && code != HttpServletResponse.SC_PARTIAL_CONTENT)
			{
				throw new DownloadException("Server does not support mutil thread download.");
			}

			return conn.getInputStream();
		}
		catch (IOException e)
		{
			throw new DownloadException("Open download connection failed.", e);
		}
	}

	/**
	 * 下载是否完成
	 * 
	 * @return
	 */
	public boolean isFinish()
	{
		return statusData.isFinish();
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
	 * 获取statusData
	 * 
	 * @return
	 */
	public ThreadStatusData getStatusData()
	{
		return statusData;
	}
}
