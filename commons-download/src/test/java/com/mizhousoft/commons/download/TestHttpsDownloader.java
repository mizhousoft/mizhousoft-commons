package com.mizhousoft.commons.download;

import java.io.InputStream;
import java.security.KeyStore;

/**
 * Main
 *
 * @version
 */
public class TestHttpsDownloader
{
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		String url = "https://localhost:18243/ums/upload/test.zip";
		String localFilePath = "E:\\test.zip";

		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		char[] passwd = "EydKOgDsXm3BxT9qdQDcFA".toCharArray();
		InputStream istream = TestHttpsDownloader.class.getClassLoader().getResourceAsStream("ota.truststore");
		trustStore.load(istream, passwd);

		HttpsDownloader downloader = new HttpsDownloader("v48289", url, localFilePath, trustStore);
		downloader.start();
	}
}
