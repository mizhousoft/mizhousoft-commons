package com.mizhousoft.commons.download;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/**
 * 文件下载器
 *
 * @version
 */
public class HttpsDownloader extends HttpDownloader
{
	// 下载信任证书
	private final KeyStore trustStore;

	/**
	 * 构造函数
	 *
	 * @param mark
	 * @param fileUrl
	 * @param localFilePath
	 * @param trustStore
	 */
	public HttpsDownloader(String mark, String fileUrl, String localFilePath, KeyStore trustStore)
	{
		super(mark, fileUrl, localFilePath);

		this.trustStore = trustStore;
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

			SSLSocketFactory sf = buildSSLSocketFactory();
			conn.setSSLSocketFactory(sf);

			return conn;
		}
		catch (IOException e)
		{
			throw new DownloadException("Open download connection failed.", e);
		}
	}

	/**
	 * 构建SSLSocketFactory
	 * 
	 * @return
	 * @throws SecurityException
	 */
	public SSLSocketFactory buildSSLSocketFactory() throws SecurityException
	{
		try
		{
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(trustStore);
			TrustManager[] tms = tmf.getTrustManagers();

			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, tms, new java.security.SecureRandom());

			return sc.getSocketFactory();
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new SecurityException("TLS algorithm does not support.", e);
		}
		catch (KeyStoreException e)
		{
			throw new SecurityException("TrustManager load truststore failed.", e);
		}
		catch (KeyManagementException e)
		{
			throw new SecurityException("SSLContext init failed.", e);
		}
	}
}
