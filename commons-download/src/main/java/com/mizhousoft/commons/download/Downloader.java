package com.mizhousoft.commons.download;

/**
 * 下载器
 *
 * @version
 */
public interface Downloader
{
	/**
	 * 开始下载
	 * 
	 * @throws DownloadException
	 */
	void start() throws DownloadException;
}
