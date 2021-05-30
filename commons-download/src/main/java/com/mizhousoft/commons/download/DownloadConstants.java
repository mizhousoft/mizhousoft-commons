package com.mizhousoft.commons.download;

/**
 * 下载常量
 *
 * @version
 */
interface DownloadConstants
{
	/**
	 * 下载重试次数
	 */
	int RETRY_NUMBER = 5;

	/**
	 * 重试延迟时间，单位是毫秒
	 */
	int RETRY_DELAY_TIME = 30 * 1000;

	/**
	 * 连接超时时间，单位是毫秒
	 */
	int CONNECT_TIMEOUT = 30 * 1000;

	/**
	 * 读取超时时间，单位是毫秒, 2h * 60m * 60s * 1000
	 */
	int READ_TIMEOUT = 7200000;
}
