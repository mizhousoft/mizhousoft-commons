package com.mizhousoft.commons.download;

/**
 * Main
 *
 * @version
 */
public class TestHttpDownloader
{
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");

		String url = "http://update8.hicloud.com:8180/TDS/data/files/p3/s15/G1018/g223/v48289/f1/full/update.zip";
		String localFilePath = "E:\\test.zip";

		HttpDownloader downloader = new HttpDownloader("v48289", url, localFilePath);
		downloader.setVerbose(true);
		downloader.start();
	}
}
