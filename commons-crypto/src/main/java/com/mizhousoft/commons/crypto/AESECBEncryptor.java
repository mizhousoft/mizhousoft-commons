package com.mizhousoft.commons.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.ArrayUtils;

/**
 * AES ECB加解密器
 *
 * @version
 */
public final class AESECBEncryptor
{
	// AES_128_KEY_LEN
	private static final int AES_128_KEY_LEN = 16;

	/**
	 * 构造函数
	 *
	 */
	private AESECBEncryptor()
	{

	}

	/**
	 * 加密
	 * 
	 * @param plainBytes
	 * @param secretKeyBytes
	 * @return
	 * @throws CryptoException
	 */
	public static byte[] encrypt(byte[] plainBytes, byte[] secretKeyBytes) throws CryptoException
	{
		if (ArrayUtils.isEmpty(plainBytes))
		{
			return null;
		}

		// 判断Key是否为16位
		if (null == secretKeyBytes || secretKeyBytes.length != AES_128_KEY_LEN)
		{
			throw new CryptoException("Secret key is illegal.");
		}

		try
		{
			SecretKeySpec skeySpec = new SecretKeySpec(secretKeyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encryptBytes = cipher.doFinal(plainBytes);

			return encryptBytes;
		}
		catch (Exception e)
		{
			throw new CryptoException("Data encrypt failed.", e);
		}
	}

	/**
	 * 解密
	 * 
	 * @param encryptBytes
	 * @param secretKeyBytes
	 * @return
	 * @throws CryptoException
	 */
	public static byte[] decrypt(byte[] encryptBytes, byte[] secretKeyBytes) throws CryptoException
	{
		if (ArrayUtils.isEmpty(encryptBytes))
		{
			return null;
		}

		// 判断Key是否正确
		if (secretKeyBytes == null || secretKeyBytes.length != AES_128_KEY_LEN)
		{
			throw new CryptoException("Secret key is illegal.");
		}

		try
		{
			SecretKeySpec skeySpec = new SecretKeySpec(secretKeyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			byte[] plainBytes = cipher.doFinal(encryptBytes);
			return plainBytes;
		}
		catch (Exception e)
		{
			throw new CryptoException("Data decrypt failed.", e);
		}
	}
}
