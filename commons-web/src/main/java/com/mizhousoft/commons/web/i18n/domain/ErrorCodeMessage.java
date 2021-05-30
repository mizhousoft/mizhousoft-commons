package com.mizhousoft.commons.web.i18n.domain;

import java.io.Serializable;

/**
 * 错误码Message
 * 
 * @version
 */
public class ErrorCodeMessage implements Serializable
{
	private static final long serialVersionUID = 6409063265732544428L;

	// 错误码
	private final String errorCode;

	// 描述
	private final String description;

	// 原因
	private final String cause;

	// 解决方案
	private final String solution;

	/**
	 * 构造函数
	 * 
	 * @param errorCode
	 * @param description
	 * @param cause
	 * @param solution
	 */
	public ErrorCodeMessage(String errorCode, String description, String cause, String solution)
	{
		super();
		this.errorCode = errorCode;
		this.description = description;
		this.cause = cause;
		this.solution = solution;
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
	 * 获取description
	 * 
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * 获取cause
	 * 
	 * @return
	 */
	public String getCause()
	{
		return cause;
	}

	/**
	 * 获取solution
	 * 
	 * @return
	 */
	public String getSolution()
	{
		return solution;
	}
}
