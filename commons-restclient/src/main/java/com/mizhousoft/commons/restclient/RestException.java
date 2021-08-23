package com.mizhousoft.commons.restclient;

import com.mizhousoft.commons.data.NestedRuntimeException;

/**
 * Rest异常
 *
 * @version
 */
public class RestException extends NestedRuntimeException
{
	private static final long serialVersionUID = -4310073232484042915L;

	// 状态码
	private final int statusCode;

	// 响应体
	private final String responseBody;

	/**
	 * 构造函数
	 *
	 * @param statusCode
	 * @param responseBody
	 * @param message
	 * @param throwable
	 */
	public RestException(int statusCode, String responseBody, String message, Throwable throwable)
	{
		super(message, throwable);

		this.statusCode = statusCode;
		this.responseBody = responseBody;
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * @param throwable
	 */
	public RestException(String message, Throwable throwable)
	{
		super(message, throwable);

		this.statusCode = -1;
		this.responseBody = null;
	}

	/**
	 * 获取statusCode
	 * 
	 * @return
	 */
	public int getStatusCode()
	{
		return statusCode;
	}

	/**
	 * 获取responseBody
	 * 
	 * @return
	 */
	public String getResponseBody()
	{
		return responseBody;
	}
}
