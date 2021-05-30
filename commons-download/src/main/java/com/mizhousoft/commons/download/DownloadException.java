package com.mizhousoft.commons.download;

import com.mizhousoft.commons.data.NestedException;

/**
 * 下载异常
 *
 * @version
 */
public class DownloadException extends NestedException
{
	private static final long serialVersionUID = -1992151827852963029L;

	/**
	 * 构造函数
	 *
	 * @param message
	 * @param cause
	 */
	public DownloadException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 */
	public DownloadException(String message)
	{
		super(message);
	}
}
