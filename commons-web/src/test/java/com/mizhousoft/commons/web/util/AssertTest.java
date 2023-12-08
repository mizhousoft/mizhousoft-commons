package com.mizhousoft.commons.web.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.web.AssertionException;

/**
 * Asserts Test
 *
 */
public class AssertTest
{
	@Test
	public void notMatch()
	{
		try
		{
			Assert.notMatch("test", "^[a-zA-Z0-9-\\u4e00-\\u9fa5]+$", "commons.web.value.error", "name");
		}
		catch (AssertionException e)
		{
			Assertions.fail(e);
		}

		try
		{
			Assert.notMatch("te&st", "^[a-zA-Z0-9-\\u4e00-\\u9fa5]+$", "commons.web.value.error", "name");

			Assertions.fail();
		}
		catch (AssertionException e)
		{

		}
	}
}
