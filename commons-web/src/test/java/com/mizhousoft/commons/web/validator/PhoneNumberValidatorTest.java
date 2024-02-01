package com.mizhousoft.commons.web.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * PhoneNumberValidator Test
 *
 * @version
 */
public class PhoneNumberValidatorTest
{
	@Test
	public void isValid()
	{
		boolean valid = PhoneNumberValidator.isLegal("19773632258");
		Assertions.assertTrue(valid);
	}
}
