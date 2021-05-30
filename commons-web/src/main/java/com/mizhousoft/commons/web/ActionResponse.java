package com.mizhousoft.commons.web;

/**
 * 操作响应
 *
 * @version
 */
public class ActionResponse
{
	// 是否成功
	private boolean okey;

	// 错误信息
	private String error;

	// 错误码
	private String errorCode;

	// 跳转
	private String location;

	/**
	 * 获取okey
	 * 
	 * @return
	 */
	public boolean isOkey()
	{
		return okey;
	}

	/**
	 * 设置okey
	 * 
	 * @param okey
	 */
	public void setOkey(boolean okey)
	{
		this.okey = okey;
	}

	/**
	 * 获取error
	 * 
	 * @return
	 */
	public String getError()
	{
		return error;
	}

	/**
	 * 设置error
	 * 
	 * @param error
	 */
	public void setError(String error)
	{
		this.error = error;
	}

	/**
	 * 获取errorCode
	 * 
	 * @return
	 */
	public String getErrorCode()
	{
		return errorCode;
	}

	/**
	 * 设置errorCode
	 * 
	 * @param errorCode
	 */
	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}

	/**
	 * 获取location
	 * 
	 * @return
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * 设置location
	 * 
	 * @param location
	 */
	public void setLocation(String location)
	{
		this.location = location;
	}
}
