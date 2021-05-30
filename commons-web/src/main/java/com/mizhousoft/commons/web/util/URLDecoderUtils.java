package com.mizhousoft.commons.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.commons.lang.CharEncoding;

/**
 * URL解码工具类
 *
 * @version
 */
public abstract class URLDecoderUtils
{
	/**
	 * 解码
	 * 
	 * @param s
	 * @return
	 */
	public static String decode(String s)
	{
		if (StringUtils.isBlank(s))
		{
			return s;
		}

		try
		{
			return URLDecoder.decode(s, CharEncoding.UTF8_NAME);
		}
		catch (UnsupportedEncodingException e)
		{
			throw new IllegalStateException(CharEncoding.UTF8_NAME + " not found.");
		}
	}
}
