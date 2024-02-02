package com.mizhousoft.commons.crypto;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.lang.CharEncoding;
import com.mizhousoft.commons.lang.HexUtils;

/**
 * TestAESEncryptor
 *
 * @version
 */
public class AESEncryptorTest
{
	@Test
	public void testEncrypt()
	{
		try
		{
			byte[] encryptBytes = "Hello World".getBytes(CharEncoding.UTF8);
			byte[] secretKeyBytes = "ABCDEFGHIJKLMNOP".getBytes(CharEncoding.UTF8);

			byte[] result = AESEncryptor.encrypt(encryptBytes, secretKeyBytes);

			System.out.println("密文(hex)：" + HexUtils.encodeHexString(result, true));
			System.out.println("密文(base64)：" + Base64.encodeBase64String(result));

			result = AESEncryptor.decrypt(result, secretKeyBytes);
			System.out.println("结果：" + new String(result));
		}
		catch (CryptoException e)
		{
			Assertions.fail(e);
		}
	}
}
