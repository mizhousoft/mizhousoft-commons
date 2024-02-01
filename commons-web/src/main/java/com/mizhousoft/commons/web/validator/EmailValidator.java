package com.mizhousoft.commons.web.validator;

import com.mizhousoft.commons.web.AssertionException;
import com.mizhousoft.commons.web.Validator;
import com.mizhousoft.commons.web.util.Assert;

/**
 * Email验证器
 *
 */
public abstract class EmailValidator implements Validator
{
	public static final String REGEX = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";

	public static void validate(String value) throws AssertionException
	{
		Assert.notMatch(value, REGEX, "Email is illegal.");
	}

	public static void validate(String fieldName, String value, String errorCode) throws AssertionException
	{
		Assert.notMatch(fieldName, value, REGEX, errorCode);
	}
}
