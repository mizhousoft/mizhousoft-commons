package com.mizhousoft.commons.crypto.generator;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

import com.mizhousoft.commons.lang.HexUtils;

/**
 * 随机生成器
 *
 * @version
 */
public final class RandomGenerator
{
	private RandomGenerator()
	{

	}

	/**
	 * 生成Key
	 * 
	 * @param size
	 * @return
	 */
	public static byte[] generateKey(int size)
	{
		byte[] iv = new byte[size];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(iv);

		return iv;
	}

	/**
	 * 生成Hex字符串
	 * 
	 * @param size
	 * @param toLowerCase
	 * @return
	 */
	public static String genHexString(int size, final boolean toLowerCase)
	{
		byte[] bytes = generateKey(size);
		return HexUtils.encodeHexString(bytes, toLowerCase);
	}

	/**
	 * 生成Base64编码字符串
	 * 
	 * @param size
	 * @return
	 */
	public static String genBase64String(int size)
	{
		byte[] bytes = generateKey(size);
		return Base64.encodeBase64String(bytes);
	}
}
