package com.mizhousoft.commons.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.crypto.generator.PBEPasswdGenerator;

/**
 * TestPBEPasswdGenerator
 *
 * @version
 */
public class TestPBEPasswdGeneratorImpl
{
	@Test
	public void testDerivePasswd()
	{
		try
		{
			String passwd = "Test";
			String salt = "sliuwerlsxousse3";

			String encPasswd = PBEPasswdGenerator.derivePasswd(passwd, salt);
			System.out.println(encPasswd);

			Assertions.assertEquals(encPasswd,
			        "Z62CL9sRrKNcBMKJgi45YUlwZyxO5BsgFtOuMVVC/SmmFbIUPGTgl4FbFU+SVnJqGEa3b7eoWYZOSYEISeKiaw==");
		}
		catch (CryptoException e)
		{
			Assertions.fail(e);
		}
	}
}
