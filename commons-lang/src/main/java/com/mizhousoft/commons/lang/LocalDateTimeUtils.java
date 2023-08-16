package com.mizhousoft.commons.lang;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

	public static LocalDateTime firstDayOfWeek()
	{
		return LocalDateTime.of(LocalDateUtils.firstDayOfWeek(), LocalTime.MIN);
	}

	public static LocalDateTime lastDayOfWeek()
	{
		return LocalDateTime.of(LocalDateUtils.lastDayOfWeek(), LocalTime.MAX);
	}

	public static LocalDateTime firstDayOfMonth()
	{
		return LocalDateTime.of(LocalDateUtils.firstDayOfMonth(), LocalTime.MIN);
	}

	public static LocalDateTime lastDayOfMonth()
	{
		return LocalDateTime.of(LocalDateUtils.lastDayOfMonth(), LocalTime.MAX);
	}

	public static LocalDateTime firstDayOfYear()
	{
		return LocalDateTime.of(LocalDateUtils.firstDayOfYear(), LocalTime.MIN);
	}

	public static LocalDateTime lastDayOfYear()
	{
		return LocalDateTime.of(LocalDateUtils.lastDayOfYear(), LocalTime.MAX);
	}

	public static LocalDateTime toLocalDateTime(Date date)
	{
		ZoneId zoneId = ZoneId.systemDefault();

		Instant instant = date.toInstant();
		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

		return localDateTime;
	}

	public static Date toDate(LocalDateTime dt)
	{
		ZoneId zoneId = ZoneId.systemDefault();

		ZonedDateTime zdt = dt.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());

		return date;
	}
}
