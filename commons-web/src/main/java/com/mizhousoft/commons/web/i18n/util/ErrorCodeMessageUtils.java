package com.mizhousoft.commons.web.i18n.util;

import java.util.Locale;

import com.mizhousoft.commons.web.i18n.domain.ErrorCodeMessage;

/**
 * ErrorCode Message Util
 * 
 * @version
 */
public abstract class ErrorCodeMessageUtils
{
	/**
	 * 获取ErrorCodeMessage
	 * 
	 * @param errorCode
	 * @return
	 */
	public static ErrorCodeMessage getMessage(String errorCode)
	{
		return getMessage(errorCode, null, null);
	}

	/**
	 * 获取ErrorCodeMessage
	 * 
	 * @param errorCode
	 * @param args
	 * @return
	 */
	public static ErrorCodeMessage getMessage(String errorCode, Object[] args)
	{
		return getMessage(errorCode, args, null);
	}

	/**
	 * 获取ErrorCodeMessage
	 * 
	 * @param errorCode
	 * @param args
	 * @param locale
	 * @return
	 */
	public static ErrorCodeMessage getMessage(String errorCode, Object[] args, Locale locale)
	{
		if (null == locale)
		{
			locale = LocaleProviderFactory.getDefault();
		}

		return InternalMessageUtils.getErrorCodeMessage(errorCode, args, locale);
	}
}
