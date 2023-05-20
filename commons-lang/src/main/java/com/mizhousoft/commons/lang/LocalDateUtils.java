package com.mizhousoft.commons.lang;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 工具类
 *
 * @version
 */
public abstract class LocalDateUtils
{
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
		return LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	}

	public static LocalDate lastDayOfWeek()
	{
		return LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
	}

	public static LocalDate firstDayOfMonth()
	{
		return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
	}

	public static LocalDate lastDayOfMonth()
	{
		return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
	}

	public static LocalDate firstDayOfYear()
	{
		return LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
	}

	public static LocalDate lastDayOfYear()
	{
		return LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
	}
}
