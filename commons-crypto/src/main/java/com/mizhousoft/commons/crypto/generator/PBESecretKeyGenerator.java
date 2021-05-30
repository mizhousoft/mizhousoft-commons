package com.mizhousoft.commons.crypto.generator;

import com.mizhousoft.commons.crypto.CryptoException;

/**
 * PBKDF2秘钥导出算法
 *
 * @version
 */
public final class PBESecretKeyGenerator
{
	// 默认迭代次数
	public static final int DEFAULT_ITERATION = 10000;

	private PBESecretKeyGenerator()
	{

	}

	/**
	 * 导出秘钥
	 * 
	 * @param size
	 * @param pass
	 * @param saltBytes
	 * @return
	 * @throws CryptoException
	 */
	public static byte[] deriveKey(int size, byte[] pass, byte[] saltBytes) throws CryptoException
	{
		return PBEGenerator.deriveKey(size, pass, saltBytes, DEFAULT_ITERATION);
	}
}
