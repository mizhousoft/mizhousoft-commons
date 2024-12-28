package com.mizhousoft.commons.lang;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 字符串工具类
 *
 * @version
 */
public abstract class StringUtils
{
	private static final String SEPARATOR = ",";

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

	/**
	 * 整数拼接成字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String join(Set<Integer> list)
	{
		return org.apache.commons.lang3.StringUtils.join(list, SEPARATOR);
	}

	/**
	 * 字符串分割成整数集合
	 * 
	 * @param data
	 * @return
	 */
	public static Set<Integer> splitToIntSet(String data)
	{
		String[] values = org.apache.commons.lang3.StringUtils.split(data, SEPARATOR);

		if (!ArrayUtils.isEmpty(values))
		{
			Set<Integer> results = new HashSet<>(values.length);
			for (String value : values)
			{
				results.add(Integer.valueOf(value));
			}

			return results;
		}
		else
		{
			return new HashSet<>(0);
		}
	}
}
