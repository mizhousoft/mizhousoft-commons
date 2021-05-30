package com.mizhousoft.commons.io.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.io.FileUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.commons.io.ZipException;

/**
 * unzip工具类
 *
 * @version
 */
public class ZipUtils
{
	private static final Logger LOG = LoggerFactory.getLogger(ZipUtils.class);

	/**
	 * 是否有效的ZIP文件
	 * 
	 * @param file
	 * @return
	 * @throws ZipException
	 */
	public static void checkZip(File file) throws ZipException
	{
		ZipFile zipFile = null;

		try
		{
			zipFile = new ZipFile(file);

			Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.getEntries();
			while (entries.hasMoreElements())
			{
				ZipEntry zipEntry = entries.nextElement();
				String filename = zipEntry.getName();

				validateFilename(filename, ".");
			}
		}
		catch (IOException e)
		{
			throw new ZipException("Validate zip file failed.", e);
		}
		finally
		{
			closeZipFile(zipFile);
		}
	}

	/**
	 * 校验文件名是否合法
	 * 
	 * @param filename
	 * @param intendedDir
	 * @throws IOException
	 * @throws ZipException
	 */
	private static void validateFilename(String filename, String intendedDir) throws IOException, ZipException
	{
		File f = new File(filename);
		String canonicalPath = f.getCanonicalPath();

		File iD = new File(intendedDir);
		String canonicalID = iD.getCanonicalPath();

		if (!canonicalPath.startsWith(canonicalID))
		{
			throw new ZipException("File is outside extraction target directory, file name is " + filename + '.');
		}
	}

	/**
	 * 解压文件
	 * 
	 * @param file
	 * @param unzipPath
	 * @throws ZipException
	 */
	public static void unzip(File file, String unzipPath) throws ZipException
	{
		File unzipDir = new File(unzipPath);
		if (unzipDir.exists() && unzipDir.isDirectory())
		{
			deleteSubFiles(unzipDir);
		}
		else
		{
			boolean ok = unzipDir.mkdirs();
			if (!ok)
			{
				throw new ZipException("Create directory failed, directory is " + unzipPath + ".");
			}
		}

		ZipFile zipFile = null;

		try
		{
			zipFile = new ZipFile(file);
			doUnzip(zipFile, unzipPath);
		}
		catch (IOException e)
		{
			throw new ZipException(e.getMessage(), e);
		}
		catch (ZipException e)
		{
			try
			{
				deleteSubFiles(unzipDir);
			}
			catch (Throwable e1)
			{
				LOG.error(e1.getMessage(), e1);
			}

			throw e;
		}
		finally
		{
			closeZipFile(zipFile);
		}
	}

	/**
	 * 删除子文件
	 * 
	 * @param parentFile
	 * @throws ZipException
	 */
	private static void deleteSubFiles(File parentFile) throws ZipException
	{
		if (!parentFile.exists())
		{
			return;
		}

		File[] subFiles = parentFile.listFiles();
		if (null != subFiles)
		{
			for (File subFile : subFiles)
			{
				try
				{
					FileUtils.forceDelete(subFile);
				}
				catch (IOException e)
				{
					throw new ZipException(e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * 解压文件
	 * 
	 * @param zipFile
	 * @param unzipPath
	 * @throws ZipException
	 */
	private static void doUnzip(ZipFile zip, String unzipPath) throws ZipException
	{
		Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.getEntries();
		while (entries.hasMoreElements())
		{
			ZipEntry zipEntry = entries.nextElement();
			if (zipEntry.isDirectory())
			{
				String unzipFileDir = unzipPath + File.separator + zipEntry.getName();
				File unzipFileDirFile = new File(unzipFileDir);
				if (!unzipFileDirFile.exists())
				{
					boolean ok = unzipFileDirFile.mkdirs();
					if (!ok)
					{
						throw new ZipException("Create directory failed, directory is " + unzipFileDir + ".");
					}
				}
			}
			else
			{
				String path = unzipPath + File.separator + zipEntry.getName();

				try (InputStream is = zip.getInputStream(zipEntry);
				        FileOutputStream fos = new FileOutputStream(new File(path)))
				{

					byte[] buff = new byte[2048];
					int size = is.read(buff);
					while (size > 0)
					{
						fos.write(buff, 0, size);
						size = is.read(buff);
					}

					fos.flush();
				}
				catch (IOException e)
				{
					throw new ZipException(e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * 关闭zip流
	 * 
	 * @param zipFile
	 */
	public static void closeZipFile(ZipFile zipFile)
	{
		if (null != zipFile)
		{
			try
			{
				zipFile.close();
			}
			catch (IOException e)
			{
				LOG.error(e.getMessage(), e);
			}
		}
	}
}
