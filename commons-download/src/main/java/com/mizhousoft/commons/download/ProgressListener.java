package com.mizhousoft.commons.download;

/**
 * 下载进度监听器
 *
 * @version
 */
interface ProgressListener
{
	/**
	 * 下载失败
	 * 
	 * @param cause
	 */
	void error(DownloadException cause);
}
