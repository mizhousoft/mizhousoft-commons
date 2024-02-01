package com.mizhousoft.commons.web.validator;

import java.util.stream.IntStream;

import com.mizhousoft.commons.web.AssertionException;
import com.mizhousoft.commons.web.Validator;

/**
 * 身份证验证器
 *
 */
public abstract class IDNumberValidator implements Validator
{
	/**
	 * 身份证校验码
	 */
	private static final int[] COEFFICIENT_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	/**
	 * 身份证号的尾数规则
	 */
	private static final String[] IDENTITY_MANTISSA = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

	/**
	 * 15位正则表达式
	 */
	private static final String IDENTITY_PATTERN_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0-2]\\d)|3[0-1])\\d{3}$";

	/**
	 * 18位正则表达式
	 */
	private static final String IDENTITY_PATTERN_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0-2]\\d)|3[0-1])\\d{3}[0-9Xx]$";

	public static void validate(String value) throws AssertionException
	{
		if (!isLegalIDNumber(value))
		{
			throw new AssertionException("IDNumber is illegal.");
		}
	}

	public static void validate(String fieldName, String value, String errorCode) throws AssertionException
	{
		if (!isLegalIDNumber(value))
		{
			throw new AssertionException(errorCode, "IDNumber is illegal.");
		}
	}

	public static boolean isLegalIDNumber(String identity)
	{
		if (identity == null)
		{
			return false;
		}
		else if (identity.length() == 15)
		{
			return is15BitLegalIDNumber(identity);
		}
		else if (identity.length() == 18)
		{
			return is18BitLegalIDNumber(identity);
		}

		return false;
	}

	private static boolean is15BitLegalIDNumber(String identity)
	{
		return identity.matches(IDENTITY_PATTERN_15);
	}

	private static boolean is18BitLegalIDNumber(String identity)
	{
		if (!identity.matches(IDENTITY_PATTERN_18))
		{
			return false;
		}

		// 将字符串对象中的字符转换为一个字符数组
		char[] chars = identity.toCharArray();
		long sum = IntStream.range(0, 17).map(index -> {
			char ch = chars[index];
			// 通俗理解：digit()是个边界值判断，不过边界返回字符数字本身数值，超过边界即返回 -1.
			int digit = Character.digit(ch, 10);
			int coefficient = COEFFICIENT_LIST[index];
			return digit * coefficient;
		}).summaryStatistics().getSum();

		// 计算出的尾数索引
		int mantissaIndex = (int) (sum % 11);
		String mantissa = IDENTITY_MANTISSA[mantissaIndex];

		String lastChar = identity.substring(17);
		return lastChar.equalsIgnoreCase(mantissa);
	}
}
