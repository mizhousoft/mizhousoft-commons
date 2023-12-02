package com.mizhousoft.commons.web.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.web.AssertionException;

/**
 * Asserts Test
 *
 */
public class AssertsTest
{
	@Test
	public void notMatch()
	{
		try
		{
			Asserts.notMatch("test", "^[a-zA-Z0-9-\\u4e00-\\u9fa5]+$", "commons.web.value.error");
		}
		catch (AssertionException e)
		{
			Assertions.fail(e);
		}

		try
		{
			Asserts.notMatch("te&st", "^[a-zA-Z0-9-\\u4e00-\\u9fa5]+$", "commons.web.value.error");

			Assertions.fail();
		}
		catch (AssertionException e)
		{

		}
	}
}
