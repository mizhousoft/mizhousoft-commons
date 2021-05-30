package com.mizhousoft.commons.crypto.generator;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

import com.mizhousoft.commons.crypto.CryptoException;

/**
 * PBKDF2导出算法
 *
 * @version
 */
public final class PBEGenerator
{
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
	public static byte[] deriveKey(int size, byte[] pass, byte[] saltBytes, int iterations) throws CryptoException
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
	private static byte[] doDeriveKey(int size, byte[] pass, byte[] saltBytes, int iterations) throws CryptoException
	{
		try
		{
			PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator(new SHA256Digest());
			gen.init(pass, saltBytes, iterations);
			byte[] derivedKey = ((KeyParameter) gen.generateDerivedParameters(size * 8)).getKey();
			return derivedKey;
		}
		catch (Exception e)
		{
			throw new CryptoException("Derive key failed.", e);
		}
	}
}
