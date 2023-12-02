package com.mizhousoft.commons.web.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.mizhousoft.commons.web.AssertionException;

/**
 * 断言
 *
 */
public abstract class Asserts
{
	public static <T> void notNull(T object, String errorCode) throws AssertionException
	{
		if (object == null)
		{
			throw new AssertionException(errorCode, "Object is null.");
		}
	}

	public static <T> void notEmpty(T[] array, String errorCode) throws AssertionException
	{
		if (ArrayUtils.isEmpty(array))
		{
			throw new AssertionException(errorCode, "Array is null.");
		}
	}

	public static <T> void notEmpty(Collection<T> collection, String errorCode) throws AssertionException
	{
		if (CollectionUtils.isEmpty(collection))
		{
			throw new AssertionException(errorCode, "Collection is null.");
		}
	}

	public static void notEmpty(Map<?, ?> map, String errorCode) throws AssertionException
	{
		if (CollectionUtils.isEmpty(map))
		{
			throw new AssertionException(errorCode, "Map is null.");
		}
	}

	public static <T> void notContain(T value, Collection<T> values, String errorCode) throws AssertionException
	{
		if (!values.contains(value))
		{
			throw new AssertionException(errorCode, "Collection does not contain " + value + ".");
		}
	}

	public static void notEquals(int actualValue, int expectedValue, String errorCode) throws AssertionException
	{
		if (actualValue != expectedValue)
		{
			throw new AssertionException(errorCode, "Value is illegal, actual value is " + actualValue + ".");
		}
	}

	public static void notEquals(String actualValue, String expectedValue, String errorCode) throws AssertionException
	{
		if (!StringUtils.equals(actualValue, expectedValue))
		{
			throw new AssertionException(errorCode, "Value is illegal, actual value is " + actualValue + ".");
		}
	}

	public static void notMatch(String value, String regex, String errorCode) throws AssertionException
	{
		try
		{
			Pattern pattern = Pattern.compile(regex);
			Matcher m = pattern.matcher(value);
			if (!m.matches())
			{
				throw new AssertionException(errorCode, "Value is illegal.");
			}
		}
		catch (PatternSyntaxException e)
		{
			throw new AssertionException(errorCode, "Pattern is illegal, regex is " + regex + ".");
		}
	}

	public static void size(String value, int min, int max, String errorCode) throws AssertionException
	{
		if (null == value)
		{
			return;
		}

		String data = value.trim();
		if (data.length() < min || data.length() > max)
		{
			String[] params = { String.valueOf(min), String.valueOf(max) };

			throw new AssertionException(errorCode, params, "Value is illegal, value is " + value + ".");
		}
	}

	public static void range(int value, int min, int max, String errorCode) throws AssertionException
	{
		if (value < min || value > max)
		{
			String[] params = { String.valueOf(min), String.valueOf(max) };

			throw new AssertionException(errorCode, params, "Value is illegal, value is " + value + ".");
		}
	}

	public static void min(int value, int min, String errorCode) throws AssertionException
	{
		if (value < min)
		{
			String[] params = { String.valueOf(min) };

			throw new AssertionException(errorCode, params, "Value is illegal, value is " + value + ".");
		}
	}

	public static void max(int value, int max, String errorCode) throws AssertionException
	{
		if (value > max)
		{
			String[] params = { String.valueOf(max) };

			throw new AssertionException(errorCode, params, "Value is illegal, value is " + value + ".");
		}
	}
}
