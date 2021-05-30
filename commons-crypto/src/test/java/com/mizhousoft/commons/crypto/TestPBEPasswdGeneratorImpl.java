package com.mizhousoft.commons.crypto;

import org.junit.Assert;
import org.junit.Test;

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

			Assert.assertEquals(encPasswd,
			        "Z62CL9sRrKNcBMKJgi45YUlwZyxO5BsgFtOuMVVC/SmmFbIUPGTgl4FbFU+SVnJqGEa3b7eoWYZOSYEISeKiaw==");
		}
		catch (CryptoException e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
