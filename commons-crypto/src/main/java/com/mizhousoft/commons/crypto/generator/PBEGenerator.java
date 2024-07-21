package com.mizhousoft.commons.crypto.generator;

import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.mizhousoft.commons.crypto.CryptoException;

/**
 * PBKDF2导出算法
 *
 * @version
 */
public final class PBEGenerator
{
	private static final String ALGORITHM_NAME = "PBKDF2WithHmacSHA256";

	/**
	 * 构造函数
	 *
	 */
	private PBEGenerator()
	{

	}

	/**
	 * 导出key
	 * 
	 * @param size
	 * @param pass
	 * @param saltBytes
	 * @param iterations
	 * @return
	 * @throws CryptoException
	 */
	public static byte[] deriveKey(int size, char[] pass, byte[] saltBytes, int iterations) throws CryptoException
	{
		if (size < 8)
		{
			throw new CryptoException("Size is illegal.");
		}

		if (null == pass || 0 == pass.length)
		{
			throw new CryptoException("Password value is null.");
		}

		if (null == saltBytes || 0 == saltBytes.length)
		{
			throw new CryptoException("Salt value is null.");
		}

		byte[] bytes = doDeriveKey(size, pass, saltBytes, iterations);
		return bytes;
	}

	/**
	 * 导出key
	 * 
	 * @param pass
	 * @param saltBytes
	 * @param iterations
	 * @return
	 * @throws CryptoException
	 */
	private static byte[] doDeriveKey(int size, char[] pass, byte[] saltBytes, int iterations) throws CryptoException
	{
		try
		{
			SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM_NAME);
			KeySpec spec = new PBEKeySpec(pass, saltBytes, iterations, size * 8);
			SecretKey key = factory.generateSecret(spec);

			byte[] derivedKey = key.getEncoded();

			return derivedKey;
		}
		catch (Exception e)
		{
			throw new CryptoException("Derive key failed.", e);
		}
	}
}
