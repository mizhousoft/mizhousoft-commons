package com.mizhousoft.commons.data.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据匿名化
 *
 * @version
 */
public abstract class DataAnonymization
{
	public static String anonyRealname(String realname)
	{
		if (null == realname)
		{
			return null;
		}

		String b = StringUtils.right(realname, 1);
		String c = StringUtils.repeat("*", realname.length() - 1);
		String value = c + b;

		return value;
	}

	public static String anonyIdcard(String idcard)
	{
		if (null == idcard)
		{
			return null;
		}

		String a = StringUtils.left(idcard, 1);
		String b = StringUtils.right(idcard, 1);
		String c = StringUtils.repeat("*", idcard.length() - 2);
		String value = a + c + b;

		return value;
	}

	public static String anonyPhoneNumber(String phoneNumber)
	{
		if (null == phoneNumber)
		{
			return null;
		}

		String a = StringUtils.left(phoneNumber, 3);
		String b = StringUtils.right(phoneNumber, 4);
		String c = StringUtils.repeat("*", phoneNumber.length() - 7);
		String value = a + c + b;

		return value;
	}

	public static String anonyBankCard(String cardNo)
	{
		if (null == cardNo)
		{
			return null;
		}

		String b = StringUtils.right(cardNo, 4);
		String c = StringUtils.repeat("*", cardNo.length() - 4);
		String value = c + b;

		String regex = "(.{4})";
		value = value.replaceAll(regex, "$1 ");

		return value;
	}
}
