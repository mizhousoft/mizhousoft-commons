package com.mizhousoft.commons.lang;

import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * DateUtils Test
 *
 * @version
 */
public class DateUtilsTest
{
	@Test
	public void formatMd()
	{
		System.out.println(DateUtils.formatMd(System.currentTimeMillis()));

		System.out.println(DateUtils.formatMd(new Date()));
	}
}
