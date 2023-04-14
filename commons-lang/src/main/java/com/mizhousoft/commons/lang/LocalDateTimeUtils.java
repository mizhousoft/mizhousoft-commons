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

	public static final String TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

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

	public static String formatMd(LocalDateTime localDateTime)
	{
		return format(localDateTime, "MM-dd");
	}

	public static String formatZhZone(LocalDateTime localDateTime, boolean withZone)
	{
		if (null == localDateTime)
		{
			return null;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_ZONE_FORMAT);
		String format = localDateTime.atZone(DEFAULT_ZONE_OFFSET).format(formatter);

		if (withZone)
		{
			format = format + "+08:00";
		}

		return format;
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

	public static LocalDateTime parse(String input)
	{
		return parse(input, DEFAULT_PATTERN);
	}

	public static LocalDateTime parse(String input, String pattern)
	{
		if (null == input)
		{
			return null;
		}

		return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(pattern));
	}

	public static LocalDateTime parseISOOffset(String input)
	{
		if (null == input)
		{
			return null;
		}

		return LocalDateTime.parse(input, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
}
