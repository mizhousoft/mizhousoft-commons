package com.mizhousoft.commons.web.i18n.util;

import java.util.Locale;

import com.mizhousoft.commons.web.i18n.constant.Language;

/**
 * Locale Provider
 * 
 * @version
 */
public abstract class LocaleProviderFactory
{
	// 默认的语言
	private static Locale defaultLocale = new Locale(Language.ZH.getValue());

	/**
	 * 获取默认的语言
	 * 
	 * @return
	 */
	public static Locale getDefault()
	{
		return defaultLocale;
	}

	/**
	 * 设置默认的语言
	 * 
	 * @param locale
	 */
	public static void setDefault(Locale locale)
	{
		defaultLocale = locale;
	}

	/**
	 * 释放默认的语言
	 */
	public static void releaseDefault()
	{
		defaultLocale = null;
	}
}
