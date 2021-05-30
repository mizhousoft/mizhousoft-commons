package com.mizhousoft.commons.download;

/**
 * Main
 *
 * @version
 */
public class TestHttpDownloader
{
	public static void main(String[] args) throws Exception
	{
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");

		String url = "";
		String localFilePath = "E:\\test.zip";

		HttpDownloader downloader = new HttpDownloader("v48289", url, localFilePath);
		downloader.setVerbose(true);
		downloader.start();
	}
}
