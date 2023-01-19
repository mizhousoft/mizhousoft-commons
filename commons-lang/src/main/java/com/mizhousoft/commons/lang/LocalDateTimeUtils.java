package com.mizhousoft.commons.lang;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 工具类
 *
 * @version
 */
public abstract class LocalDateTimeUtils
{
	public static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of("+8");

	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String formatYmdhms(LocalDateTime localDateTime)
	{
		return format(localDateTime, DEFAULT_PATTERN);
	}

	public static String formatYmdhm(LocalDateTime localDateTime)
	{
		return format(localDateTime, "yyyy-MM-dd HH:mm");
	}

	public static String formatYmd(LocalDateTime localDateTime)
	{
		return format(localDateTime, "yyyy-MM-dd");
	}

	public static String formatYm(LocalDateTime localDateTime)
	{
		return format(localDateTime, "yyyy-MM");
	}

	public static String format(LocalDateTime localDateTime, String pattern)
	{
		if (null == localDateTime)
		{
			return null;
		}

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
		String format = dateTimeFormatter.format(localDateTime);
		return format;
	}

	public static long toTimestamp(LocalDateTime localDateTime)
	{
		return toTimestamp(localDateTime, DEFAULT_ZONE_OFFSET);
	}

	public static long toTimestamp(LocalDateTime localDateTime, ZoneOffset offset)
	{
		if (null == localDateTime)
		{
			return 0;
		}

		return localDateTime.toInstant(offset).toEpochMilli();
	}

	public static LocalDateTime toLocalDateTime(long second, ZoneOffset offset)
	{
		return LocalDateTime.ofEpochSecond(second, 0, offset);
	}

	public static LocalDateTime toLocalDateTime(long second)
	{
		if (0 == second)
		{
			return null;
		}

		return LocalDateTime.ofEpochSecond(second, 0, DEFAULT_ZONE_OFFSET);
	}

	public static LocalDateTime parse(String dt)
	{
		return parse(dt, DEFAULT_PATTERN);
	}

	public static LocalDateTime parse(String dt, String pattern)
	{
		if (null == dt)
		{
			return null;
		}

		LocalDateTime parse = LocalDateTime.parse(dt, DateTimeFormatter.ofPattern(pattern));
		return parse;
	}
}
