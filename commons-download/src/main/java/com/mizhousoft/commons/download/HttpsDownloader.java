package com.mizhousoft.commons.download;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * 文件下载器
 *
 * @version
 */
public class HttpsDownloader extends HttpDownloader
{
	/**
	 * 构造函数
	 *
	 * @param mark
	 * @param fileUrl
	 * @param localFilePath
	 */
	public HttpsDownloader(String mark, String fileUrl, String localFilePath)
	{
		super(mark, fileUrl, localFilePath);
	}

	/**
	 * 打开下载连接
	 * 
	 * @return
	 * @throws DownloadException
	 */
	@Override
	public HttpURLConnection openHttpURLConnection() throws DownloadException
	{
		try
		{
			URL url = new URL(fileUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

			return conn;
		}
		catch (IOException e)
		{
			throw new DownloadException("Open download connection failed.", e);
		}
	}
}
