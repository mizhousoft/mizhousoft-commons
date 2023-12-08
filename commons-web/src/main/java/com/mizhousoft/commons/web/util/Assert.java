package com.mizhousoft.commons.web.util;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.commons.web.AssertionException;

/**
 * 断言
 *
 */
public abstract class Assert
{
	public static <T> void notNull(String fieldName, T value, String errorCode) throws AssertionException
	{
		if (value == null)
		{
			throw new AssertionException(errorCode, fieldName + " is null.");
		}
	}

	public static void notBlank(String fieldName, String value, String errorCode) throws AssertionException
	{
		notNull(fieldName, value, errorCode);

		if (value.trim().length() == 0)
		{
			throw new AssertionException(errorCode, fieldName + " is blank.");
		}
	}

	public static <T> void notEmpty(String fieldName, T[] values, String errorCode) throws AssertionException
	{
		if (null == values || values.length == 0)
		{
			throw new AssertionException(errorCode, fieldName + " is empty.");
		}
	}

	public static <T> void notEmpty(String fieldName, Collection<T> values, String errorCode) throws AssertionException
	{
		if (values == null || values.isEmpty())
		{
			throw new AssertionException(errorCode, fieldName + " is empty.");
		}
	}

	public static void notEmpty(String fieldName, Map<?, ?> values, String errorCode) throws AssertionException
	{
		if (values == null || values.isEmpty())
		{
			throw new AssertionException(errorCode, fieldName + " is empty.");
		}
	}

	public static <T> void notContain(String fieldName, T value, Collection<T> values, String errorCode) throws AssertionException
	{
		if (!values.contains(value))
		{
			throw new AssertionException(errorCode, fieldName + " is not in the collection.");
		}
	}

	public static void notEquals(String fieldName, int actualValue, int expectedValue, String errorCode) throws AssertionException
	{
		if (actualValue != expectedValue)
		{
			throw new AssertionException(errorCode,
			        fieldName + " is not equal to the expected value, actual value is " + actualValue + '.');
		}
	}

	public static void notEquals(String fieldName, String actualValue, String expectedValue, String errorCode) throws AssertionException
	{
		if (!StringUtils.equals(actualValue, expectedValue))
		{
			throw new AssertionException(errorCode, fieldName + " is not equal to the expected value.");
		}
	}

	public static void notMatch(String fieldName, String value, String regex, String errorCode) throws AssertionException
	{
		if (value == null)
		{
			return;
		}

		try
		{
			Pattern pattern = Pattern.compile(regex);
			Matcher m = pattern.matcher(value);
			if (!m.matches())
			{
				throw new AssertionException(errorCode, fieldName + " is illegal.");
			}
		}
		catch (PatternSyntaxException e)
		{
			throw new AssertionException(errorCode, "Pattern is illegal, regex is " + regex + ".");
		}
	}

	public static void size(String fieldName, String value, int min, int max, String errorCode) throws AssertionException
	{
		if (null == value)
		{
			return;
		}

		String data = value.trim();
		if (data.length() < min || data.length() > max)
		{
			String[] params = { String.valueOf(min), String.valueOf(max) };

			throw new AssertionException(errorCode, params,
			        fieldName + " length is not in the " + min + " to " + max + " range, length is " + value.length() + '.');
		}
	}

	public static void range(String fieldName, int value, int min, int max, String errorCode) throws AssertionException
	{
		if (value < min || value > max)
		{
			String[] params = { String.valueOf(min), String.valueOf(max) };

			throw new AssertionException(errorCode, params,
			        fieldName + " is not in the " + min + " to " + max + " range, value is " + value + '.');
		}
	}

	public static void min(String fieldName, int value, int min, String errorCode) throws AssertionException
	{
		if (value < min)
		{
			String[] params = { String.valueOf(min) };

			throw new AssertionException(errorCode, params,
			        fieldName + " is smaller than the maximum " + min + ", value is " + value + '.');
		}
	}

	public static void max(String fieldName, int value, int max, String errorCode) throws AssertionException
	{
		if (value > max)
		{
			String[] params = { String.valueOf(max) };

			throw new AssertionException(errorCode, params, fieldName + " is larger than the maximum " + max + ", value is " + value + '.');
		}
	}

	public static void min(String fieldName, long value, int min, String errorCode) throws AssertionException
	{
		if (value < min)
		{
			String[] params = { String.valueOf(min) };

			throw new AssertionException(errorCode, params,
			        fieldName + " is smaller than the maximum " + min + ", value is " + value + '.');
		}
	}

	public static void max(String fieldName, long value, int max, String errorCode) throws AssertionException
	{
		if (value > max)
		{
			String[] params = { String.valueOf(max) };

			throw new AssertionException(errorCode, params, fieldName + " is larger than the maximum " + max + ", value is " + value + '.');
		}
	}
}
