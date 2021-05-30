package com.mizhousoft.commons.data;

/**
 * NestedRuntimeException
 * 
 * @version
 */
public class NestedRuntimeException extends RuntimeException
{
	private static final long serialVersionUID = -6719792450067565198L;

	// 错误码
	protected String errorCode;

	// 错误码参数
	protected String[] codeParams;

	/**
	 * 构造函数
	 * 
	 * @param message
	 */
	public NestedRuntimeException(String message)
	{
		this(null, null, message, null);
	}

	/**
	 * 构造函数
	 * 
	 * @param errorCode
	 * @param message
	 */
	public NestedRuntimeException(String errorCode, String message)
	{
		this(errorCode, null, message, null);
	}

	/**
	 * 构造函数
	 *
	 * @param message
	 * @param throwable
	 */
	public NestedRuntimeException(String message, Throwable throwable)
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
	public NestedRuntimeException(String errorCode, String message, Throwable throwable)
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
	public NestedRuntimeException(String errorCode, String[] codeParams, String message)
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
	public NestedRuntimeException(String errorCode, String[] codeParams, String message, Throwable throwable)
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
