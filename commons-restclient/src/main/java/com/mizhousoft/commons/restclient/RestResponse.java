package com.mizhousoft.commons.restclient;

import java.util.Map;

/**
 * 响应
 *
 * @version
 */
public class RestResponse
{
	/**
	 * 状态码
	 */
	private int statusCode;

	/**
	 * body
	 */
	private String body;

	/**
	 * 响应Header
	 */
	private Map<String, String> headers;

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
	 * 设置statusCode
	 * 
	 * @param statusCode
	 */
	public void setStatusCode(int statusCode)
	{
		this.statusCode = statusCode;
	}

	/**
	 * 获取body
	 * 
	 * @return
	 */
	public String getBody()
	{
		return body;
	}

	/**
	 * 设置body
	 * 
	 * @param body
	 */
	public void setBody(String body)
	{
		this.body = body;
	}

	/**
	 * 获取headers
	 * 
	 * @return
	 */
	public Map<String, String> getHeaders()
	{
		return headers;
	}

	/**
	 * 设置headers
	 * 
	 * @param headers
	 */
	public void setHeaders(Map<String, String> headers)
	{
		this.headers = headers;
	}
}
