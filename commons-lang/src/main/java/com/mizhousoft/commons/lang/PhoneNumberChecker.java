package com.mizhousoft.commons.lang;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 *
 * @version
 */
public abstract class PhoneNumberChecker
{
	public static final String PHONE_REGEX = "^[1](([3][0-9])|([4][0,5-9])|([5][0-3,5-9])|([6][0-9])|([7][0-9])|([8][0-9])|([9][0,1,2,3,5,6,7,8,9]))[0-9]{8}$";

	private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

	public static boolean isValid(String phoneNumber)
	{
		if (null == phoneNumber || phoneNumber.length() != 11)
		{
			return false;
		}

		Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);

		return matcher.matches();
	}
}
