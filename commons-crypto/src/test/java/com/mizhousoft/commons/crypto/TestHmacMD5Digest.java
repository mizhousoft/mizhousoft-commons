package com.mizhousoft.commons.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.crypto.digest.HmacMD5Digest;
import com.mizhousoft.commons.lang.CharEncoding;
import com.mizhousoft.commons.lang.HexUtils;

/**
 * TestHmacMD5Digest
 *
 * @version
 */
public class TestHmacMD5Digest
{
	@Test
	public void testHash()
	{
		try
		{
			byte[] key = "123456".getBytes(CharEncoding.UTF8);
			byte[] data = "123456".getBytes(CharEncoding.UTF8);
			byte[] bytes = HmacMD5Digest.hash(key, data);
			String hashValue = HexUtils.encodeHexString(bytes, true);
			Assertions.assertEquals(hashValue, "30ce71a73bdd908c3955a90e8f7429ef");
		}
		catch (Exception e)
		{
			Assertions.fail(e);
		}
	}
}
