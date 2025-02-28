package com.mizhousoft.commons.lang;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 工具类
 *
 * @version
 */
public abstract class LocalDateUtils
{
	public static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of("+8");

	public static final String DEFAULT_PATTERN = "yyyy-MM-dd";

	public static String formatYmd(LocalDate localDate)
	{
		return format(localDate, DEFAULT_PATTERN);
	}

	public static String formatYm(LocalDate localDate)
	{
		return format(localDate, "yyyy-MM");
	}

	public static String formatMd(LocalDate localDate)
	{
		return format(localDate, "MM-dd");
	}

	public static String format(LocalDate localDate, String pattern)
	{
		if (null == localDate)
		{
			return null;
		}

		return localDate.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static LocalDate parseYmd(String date)
	{
		return parse(date, DEFAULT_PATTERN);
	}

	public static LocalDate parse(String date, String pattern)
	{
		if (null == date)
		{
			return null;
		}

		LocalDate parse = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
		return parse;
	}

	public static LocalDate firstDayOfWeek()
	{
		return firstDayOfWeek(LocalDate.now());
	}

	public static LocalDate firstDayOfWeek(LocalDate date)
	{
		return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	}

	public static LocalDate lastDayOfWeek()
	{
		return lastDayOfWeek(LocalDate.now());
	}

	public static LocalDate lastDayOfWeek(LocalDate date)
	{
		return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
	}

	public static LocalDate firstDayOfMonth()
	{
		return firstDayOfMonth(LocalDate.now());
	}

	public static LocalDate firstDayOfMonth(LocalDate date)
	{
		return date.with(TemporalAdjusters.firstDayOfMonth());
	}

	public static LocalDate lastDayOfMonth()
	{
		return lastDayOfMonth(LocalDate.now());
	}

	public static LocalDate lastDayOfMonth(LocalDate date)
	{
		return date.with(TemporalAdjusters.lastDayOfMonth());
	}

	public static LocalDate firstDayOfYear()
	{
		return firstDayOfYear(LocalDate.now());
	}

	public static LocalDate firstDayOfYear(LocalDate date)
	{
		return date.with(TemporalAdjusters.firstDayOfYear());
	}

	public static LocalDate lastDayOfYear()
	{
		return lastDayOfYear(LocalDate.now());
	}

	public static LocalDate lastDayOfYear(LocalDate date)
	{
		return date.with(TemporalAdjusters.lastDayOfYear());
	}

	public static long toSecond(LocalDate localDate)
	{
		return toSecond(localDate, DEFAULT_ZONE_OFFSET);
	}

	public static long toSecond(LocalDate localDate, ZoneOffset offset)
	{
		if (null == localDate)
		{
			return 0;
		}

		return localDate.toEpochSecond(LocalTime.MIDNIGHT, offset);
	}

	public static long toTimestamp(LocalDate localDate)
	{
		return toSecond(localDate) * 1000;
	}

	public static long toTimestamp(LocalDate localDate, ZoneOffset offset)
	{
		return toSecond(localDate, offset) * 1000;
	}

	public static Date toDate(LocalDate localDate)
	{
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate toLocalDate(Date date)
	{
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDate toLocalDate(long second)
	{
		if (0 == second)
		{
			return null;
		}

		return LocalDateTimeUtils.toLocalDateTime(second).toLocalDate();
	}

	/**
	 * 两组日期是否重叠
	 * 
	 * @param start1
	 * @param end1
	 * @param start2
	 * @param end2
	 * @return
	 */
	public static boolean isOverlapping(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2)
	{
		return (start1.isBefore(end2) || start1.isEqual(end2)) && (end1.isAfter(start2) || end1.isEqual(start2));
	}

}
