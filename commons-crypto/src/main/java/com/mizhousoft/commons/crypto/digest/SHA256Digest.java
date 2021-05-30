package com.mizhousoft.commons.crypto.digest;

import java.security.MessageDigest;

import com.mizhousoft.commons.crypto.CryptoException;

/**
 * SHA256HASH算法
 *
 * @version
 */
public final class SHA256Digest
{
	/**
	 * 算法名
	 */
	public static final String ALGORITHM_NAME = "SHA-256";

	/**
	 * 构造函数
	 *
	 */
	private SHA256Digest()
	{

	}

	/**
	 * Hash
	 * 
	 * @param dataBytes
	 * @return
	 * @throws CryptoException
	 */
	public static byte[] hash(byte[] dataBytes) throws CryptoException
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance(ALGORITHM_NAME);
			digest.update(dataBytes);
			byte[] result = digest.digest();
			return result;
		}
		catch (Exception e)
		{
			throw new CryptoException("SHA256 digest hash failed.", e);
		}
	}
}
