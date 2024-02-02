package com.mizhousoft.commons.crypto;

import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.crypto.generator.RandomGenerator;
import com.mizhousoft.commons.lang.HexUtils;

/**
 * RandomGenerator测试类
 *
 * @version
 */
public class RandomGeneratorTest
{
	@Test
	public void testGenerateKey()
	{
		byte[] passBytes = RandomGenerator.generateKey(16);
		String data = HexUtils.encodeHexString(passBytes, true);
		System.out.println(data);
	}
}
