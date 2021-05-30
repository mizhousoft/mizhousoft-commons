package com.mizhousoft.commons.crypto;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.ArrayUtils;

/**
 * AES加解密器
 *
 * @version
 */
public final class AESEncryptor
{
	// AES_128_KEY_LEN
	private static final int AES_128_KEY_LEN = 16;

	/**
	 * 构造函数
	 *
	 */
	private AESEncryptor()
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
		if (null == secretKeyBytes || secretKeyBytes.length != 16)
		{
			throw new CryptoException("Secret key is illegal.");
		}

		try
		{
			byte[] iv = new byte[16];
			SecureRandom secureRandom = new SecureRandom();
			secureRandom.nextBytes(iv);

			SecretKeySpec skeySpec = new SecretKeySpec(secretKeyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			IvParameterSpec ivSepc = new IvParameterSpec(iv, 0, 16);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSepc);
			byte[] data = cipher.doFinal(plainBytes);

			byte[] encryptBytes = new byte[data.length + AES_128_KEY_LEN];
			System.arraycopy(iv, 0, encryptBytes, 0, AES_128_KEY_LEN);
			System.arraycopy(data, 0, encryptBytes, AES_128_KEY_LEN, data.length);

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
		if (secretKeyBytes == null || secretKeyBytes.length != 16)
		{
			throw new CryptoException("Secret key is illegal.");
		}

		try
		{
			byte[] iv = new byte[AES_128_KEY_LEN];
			byte[] cipherBytes = new byte[encryptBytes.length - AES_128_KEY_LEN];
			System.arraycopy(encryptBytes, 0, iv, 0, AES_128_KEY_LEN);
			System.arraycopy(encryptBytes, AES_128_KEY_LEN, cipherBytes, 0, encryptBytes.length - AES_128_KEY_LEN);

			SecretKeySpec skeySpec = new SecretKeySpec(secretKeyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivSepc = new IvParameterSpec(iv, 0, 16);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSepc);

			byte[] plainBytes = cipher.doFinal(cipherBytes);
			return plainBytes;
		}
		catch (Exception e)
		{
			throw new CryptoException("Data decrypt failed.", e);
		}
	}
}
