package com.mizhousoft.commons.lang;

import java.text.Normalizer;
import java.text.Normalizer.Form;

/**
 * 字符串工具类
 *
 * @version
 */
public abstract class StringUtils
{
	/**
	 * 字符串归一化
	 * 
	 * @param data
	 * @return
	 */
	public static String normalize(String data)
	{
		if (org.apache.commons.lang3.StringUtils.isBlank(data))
		{
			return data;
		}

		return Normalizer.normalize(data, Form.NFKC);
	}

	/**
	 * byte转字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toUTF8String(final byte[] bytes)
	{
		String data = new String(bytes, CharEncoding.UTF8);
		return data;
	}
}
