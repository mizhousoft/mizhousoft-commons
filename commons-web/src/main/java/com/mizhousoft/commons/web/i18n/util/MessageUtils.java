package com.mizhousoft.commons.web.i18n.util;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * Message工具类
 * 
 * @version
 */
public abstract class MessageUtils
{
	/**
	 * 获取Message
	 * 
	 * @param code
	 * @return
	 */
	public static String getMessage(String code)
	{
		return getMessage(code, null, null);
	}

	/**
	 * 获取Message
	 * 
	 * @param code
	 * @param args
	 * @return
	 */
	public static String getMessage(String code, Object[] args)
	{
		return getMessage(code, args, null);
	}

	/**
	 * 获取Message
	 * 
	 * @param code
	 * @param args
	 * @param locale
	 * @return
	 */
	public static String getMessage(String code, Object[] args, Locale locale)
	{
		if (StringUtils.isBlank(code))
		{
			return null;
		}

		if (null == locale)
		{
			locale = LocaleProviderFactory.getDefault();
		}

		return InternalMessageUtils.getMessage(code, args, locale);
	}
}
