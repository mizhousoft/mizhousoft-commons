package com.mizhousoft.commons.crypto.digest;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.mizhousoft.commons.crypto.CryptoException;

/**
 * HmacMD5Digest
 *
 * @version
 */
public final class HmacMD5Digest
{
	private static final String ALGORITHM_NAME = "HmacMD5";

	/**
	 * 构造函数
	 *
	 */
	private HmacMD5Digest()
	{

	}

	/**
	 * HASH
	 * 
	 * @param secretKeyByte
	 * @param dataBytes
	 * @return
	 * @throws CryptoException
	 */
	public static byte[] hash(byte[] secretKeyByte, byte[] dataBytes) throws CryptoException
	{
		try
		{
			Mac mac = Mac.getInstance(ALGORITHM_NAME);
			SecretKey keySpec = new SecretKeySpec(secretKeyByte, ALGORITHM_NAME);
			mac.init(keySpec);
			byte[] doFinal = mac.doFinal(dataBytes);
			return doFinal;
		}
		catch (Exception e)
		{
			throw new CryptoException("HMAC hash failed.", e);
		}
	}
}
