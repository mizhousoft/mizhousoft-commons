package com.mizhousoft.commons.download;

/**
 * Main
 *
 * @version
 */
public class TestHttpsDownloader
{
	public static void main(String[] args) throws Exception
	{
		String url = "https://xcsqapp.com/imgs/c1.jpg";
		String localFilePath = "c:\\work\\test.jpg";

		HttpsDownloader downloader = new HttpsDownloader("v48289", url, localFilePath);
		downloader.start();
	}
}
