package com.mizhousoft.commons.lang;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * LocalDateTimeUtils
 *
 * @version
 */
public class LocalDateTimeUtilsTest
{
	@Test
	public void parse()
	{
		String time = "2021-12-15 17:19:28";

		LocalDateTime d = LocalDateTimeUtils.parse(time);

		String result = LocalDateTimeUtils.formatYmdhms(d);

		Assertions.assertEquals(time, result);
	}
}
