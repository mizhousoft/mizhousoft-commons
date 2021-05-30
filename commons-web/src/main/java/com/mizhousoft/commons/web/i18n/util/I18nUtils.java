package com.mizhousoft.commons.web.i18n.util;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import com.mizhousoft.commons.context.util.ServiceContextHolder;

/**
 * 国际化工具类
 *
 * @version
 */
public class I18nUtils
{
	private static final Logger LOG = LoggerFactory.getLogger(I18nUtils.class);

	// MessageSource
	private static MessageSource messageSource = ServiceContextHolder.getService(MessageSource.class);

	static
	{
		LocaleContextHolder.setDefaultLocale(Locale.CHINA);
	}

	/**
	 * 获取消息
	 * 
	 * @param code
	 * @return
	 */
	public static String getMessage(String code)
	{
		Locale locale = LocaleContextHolder.getLocale();
		return getMessage(code, null, locale);
	}

	/**
	 * 获取消息
	 * 
	 * @param code
	 * @param args
	 * @return
	 */
	public static String getMessage(String code, Object[] args)
	{
		Locale locale = LocaleContextHolder.getLocale();
		return getMessage(code, args, locale);
	}

	/**
	 * 获取消息
	 * 
	 * @param code
	 * @param args
	 * @param locale
	 * @return
	 */
	public static String getMessage(String code, Object[] args, Locale locale)
	{
		if (null == code)
		{
			return null;
		}

		try
		{
			return messageSource.getMessage(code, args, locale);
		}
		catch (NoSuchMessageException e)
		{
			LOG.error("{} not found in meesage_{}.properties", code, locale.getLanguage());
			return code;
		}
	}
}
