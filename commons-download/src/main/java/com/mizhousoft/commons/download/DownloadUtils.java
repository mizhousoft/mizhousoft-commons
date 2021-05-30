package com.mizhousoft.commons.download;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 下载工具类
 *
 * @version
 */
abstract class DownloadUtils
{
	private static final Logger LOG = LoggerFactory.getLogger(DownloadUtils.class);

	/**
	 * 关闭文件读写
	 * 
	 * @param raf
	 */
	public static void closeFile(RandomAccessFile raf)
	{
		if (null != raf)
		{
			try
			{
				raf.close();
			}
			catch (Exception e)
			{
				LOG.error("Close RandomAccessFile failed.", e);
			}
		}
	}

	/**
	 * 关闭流
	 * 
	 * @param istream
	 */
	public static void closeStream(InputStream istream)
	{
		if (null != istream)
		{
			try
			{
				istream.close();
			}
			catch (Exception e)
			{
				LOG.error("Close InputStream failed.", e);
			}
		}
	}

	/**
	 * 关闭流
	 * 
	 * @param ostream
	 */
	public static void closeStream(OutputStream ostream)
	{
		if (null != ostream)
		{
			try
			{
				ostream.close();
			}
			catch (Exception e)
			{
				LOG.error("Close OutputStream failed.", e);
			}
		}
	}
}
