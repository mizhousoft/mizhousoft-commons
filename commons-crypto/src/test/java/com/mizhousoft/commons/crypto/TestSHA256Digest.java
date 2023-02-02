package com.mizhousoft.commons.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.crypto.digest.SHA256Digest;
import com.mizhousoft.commons.lang.CharEncoding;
import com.mizhousoft.commons.lang.HexUtils;

/**
 * TestSHA256Digest
 *
 * @version
 */
public class TestSHA256Digest
{
	@Test
	public void testHash()
	{
		try
		{
			String data = "123456";
			byte[] bytes = SHA256Digest.hash(data.getBytes(CharEncoding.UTF8));
			String hashValue = HexUtils.encodeHexString(bytes, true);
			Assertions.assertEquals(hashValue, "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
		}
		catch (CryptoException e)
		{
			Assertions.fail(e);
		}
	}
}
