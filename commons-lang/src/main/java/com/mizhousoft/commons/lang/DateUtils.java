package com.mizhousoft.commons.lang;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期格式化工具类
 *
 * @version
 */
public abstract class DateUtils
{
	public static String formatYm(long millis)
	{
		if (0 == millis)
		{
			return null;
		}

		return formatYmd(new Date(millis));
	}

	public static String formatYm(final Date date)
	{
		if (null == date)
		{
			return null;
		}

		return DateFormatUtils.format(date, "yyyy-MM");
	}

	public static String formatMd(long millis)
	{
		if (0 == millis)
		{
			return null;
		}

		return formatYmd(new Date(millis));
	}

	public static String formatMd(final Date date)
	{
		if (null == date)
		{
			return null;
		}

		return DateFormatUtils.format(date, "MM-dd");
	}

	public static String formatYmd(long millis)
	{
		if (0 == millis)
		{
			return null;
		}

		return formatYmd(new Date(millis));
	}

	public static String formatYmd(final Date date)
	{
		if (null == date)
		{
			return null;
		}

		return DateFormatUtils.format(date, "yyyy-MM-dd");
	}

	public static String formatYmdhm(long millis)
	{
		if (0 == millis)
		{
			return null;
		}

		return formatYmdhm(new Date(millis));
	}

	public static String formatYmdhm(final Date date)
	{
		if (null == date)
		{
			return null;
		}

		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm");
	}

	public static String formatYmdhms(long millis)
	{
		if (0 == millis)
		{
			return null;
		}

		return formatYmdhms(new Date(millis));
	}

	public static String formatYmdhms(final Date date)
	{
		if (null == date)
		{
			return null;
		}

		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static long toTime(final Date date)
	{
		if (null == date)
		{
			return 0;
		}

		return date.getTime();
	}
}
