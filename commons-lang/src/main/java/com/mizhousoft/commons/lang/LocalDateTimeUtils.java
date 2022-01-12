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

	public static String formatYmdhms(LocalDateTime localDateTime)
	{
		if (null == localDateTime)
		{
			return null;
		}

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String format = dateTimeFormatter.format(localDateTime);
		return format;
	}

	public static String formatYmdhm(LocalDateTime localDateTime)
	{
		if (null == localDateTime)
		{
			return null;
		}

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String format = dateTimeFormatter.format(localDateTime);
		return format;
	}

	public static String formatYmd(LocalDateTime localDateTime)
	{
		if (null == localDateTime)
		{
			return null;
		}

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String format = dateTimeFormatter.format(localDateTime);
		return format;
	}

	public static String formatYm(LocalDateTime localDateTime)
	{
		if (null == localDateTime)
		{
			return null;
		}

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
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
		return LocalDateTime.ofEpochSecond(second, 0, DEFAULT_ZONE_OFFSET);
	}
}
