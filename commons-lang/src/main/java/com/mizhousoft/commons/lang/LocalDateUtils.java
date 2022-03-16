package com.mizhousoft.commons.lang;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 工具类
 *
 * @version
 */
public abstract class LocalDateUtils
{
	public static String formatYmd(LocalDate localDate)
	{
		if (null == localDate)
		{
			return null;
		}

		String pattern = "yyyy-MM-dd";

		return localDate.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static LocalDate parseYmd(String date)
	{
		if (null == date)
		{
			return null;
		}

		String pattern = "yyyy-MM-dd";
		LocalDate parse = LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
		return parse;
	}
}
