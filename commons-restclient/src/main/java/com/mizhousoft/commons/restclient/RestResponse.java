package com.mizhousoft.commons.restclient;

import java.io.Serializable;

/**
 * Rest响应
 *
 * @version
 */
public class RestResponse implements Serializable
{
	private static final long serialVersionUID = -4643206672779118136L;

	// 状态值
	private int statusCode;

	// 状态描述
	private String description;

	// 错误详情
	private String errorMsg;

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
	 * 获取description
	 * 
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * 设置description
	 * 
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * 获取errorMsg
	 * 
	 * @return
	 */
	public String getErrorMsg()
	{
		return errorMsg;
	}

	/**
	 * 设置errorMsg
	 * 
	 * @param errorMsg
	 */
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
}
