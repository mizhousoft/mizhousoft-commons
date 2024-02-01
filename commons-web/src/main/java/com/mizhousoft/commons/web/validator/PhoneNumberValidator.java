package com.mizhousoft.commons.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mizhousoft.commons.web.AssertionException;
import com.mizhousoft.commons.web.Validator;
import com.mizhousoft.commons.web.util.Assert;

/**
 * 手机号验证器
 *
 */
public abstract class PhoneNumberValidator implements Validator
{
	public static final String REGEX = "^[1](([3][0-9])|([4][0,5-9])|([5][0-3,5-9])|([6][0-9])|([7][0-9])|([8][0-9])|([9][0,1,2,3,5,6,7,8,9]))[0-9]{8}$";

	private static final Pattern PATTERN = Pattern.compile(REGEX);

	public static boolean isLegal(String phoneNumber)
	{
		if (null == phoneNumber || phoneNumber.length() != 11)
		{
			return false;
		}

		Matcher matcher = PATTERN.matcher(phoneNumber);

		return matcher.matches();
	}

	public static void validate(String value) throws AssertionException
	{
		Assert.notMatch(value, REGEX, "PhoneNumber is illegal.");
	}

	public static void validate(String fieldName, String value, String errorCode) throws AssertionException
	{
		Assert.notMatch(fieldName, value, REGEX, errorCode);
	}
}
