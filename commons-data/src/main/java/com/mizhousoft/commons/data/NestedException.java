package com.mizhousoft.commons.data;

/**
 * NestedException
 * 
 * @version
 */
public class NestedException extends Exception
{
	private static final long serialVersionUID = -2292661666283627052L;

	// 错误码
	protected String errorCode;

	// 错误码参数
	protected String[] codeParams;

	/**
	 * 构造函数
	 * 
	 * @param message
	 */
	public NestedException(String message)
	{
		this(null, null, message, null);
	}

	/**
	 * 构造函数
	 * 
	 * @param errorCode
	 * @param message
	 */
	public NestedException(String errorCode, String message)
	{
		this(errorCode, null, message, null);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * @param throwable
	 */
	public NestedException(String message, Throwable throwable)
	{
		this(null, null, message, throwable);
	}

	/**
	 * 构造函数
	 * 
	 * @param errorCode
	 * @param message
	 * @param throwable
	 */
	public NestedException(String errorCode, String message, Throwable throwable)
	{
		this(errorCode, null, message, throwable);
	}

	/**
	 * 构造函数
	 * 
	 * @param errorCode
	 * @param codeParams
	 * @param message
	 */
	public NestedException(String errorCode, String[] codeParams, String message)
	{
		this(errorCode, codeParams, message, null);
	}

	/**
	 * 构造函数
	 * 
	 * @param errorCode
	 * @param codeParams
	 * @param message
	 * @param throwable
	 */
	public NestedException(String errorCode, String[] codeParams, String message, Throwable throwable)
	{
		super(message, throwable);
		this.errorCode = errorCode;
		this.codeParams = codeParams;
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
	 * 获取 codeParams
	 * 
	 * @return
	 */
	public String[] getCodeParams()
	{
		return codeParams;
	}
}
