package com.mizhousoft.commons.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * PhoneNumberChecker Test
 *
 * @version
 */
public class PhoneNumberCheckerTest
{
	@Test
	public void isValid()
	{
		boolean valid = PhoneNumberChecker.isValid("19773632258");
		Assertions.assertTrue(valid);
	}
}
