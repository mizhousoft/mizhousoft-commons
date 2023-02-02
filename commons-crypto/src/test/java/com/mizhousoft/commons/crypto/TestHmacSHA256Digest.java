package com.mizhousoft.commons.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.crypto.digest.HmacSHA256Digest;
import com.mizhousoft.commons.lang.CharEncoding;
import com.mizhousoft.commons.lang.HexUtils;

/**
 * TestHmacSHA256Digest
 *
 * @version
 */
public class TestHmacSHA256Digest
{
	@Test
	public void testHash()
	{
		try
		{
			byte[] key = "123456".getBytes(CharEncoding.UTF8);
			byte[] data = "123456".getBytes(CharEncoding.UTF8);
			byte[] bytes = HmacSHA256Digest.hash(key, data);
			String hashValue = HexUtils.encodeHexString(bytes, true);
			Assertions.assertEquals(hashValue, "b8ad08a3a547e35829b821b75370301dd8c4b06bdd7771f9b541a75914068718");
		}
		catch (Exception e)
		{
			Assertions.fail(e);
		}
	}
}
