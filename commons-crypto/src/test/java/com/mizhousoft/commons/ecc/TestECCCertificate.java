package com.mizhousoft.commons.ecc;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.crypto.CryptoException;

/**
 * ECCCertificate
 *
 * @version
 */
public class TestECCCertificate
{
	@Test
	public void test() throws CryptoException
	{
		Security.addProvider(new BouncyCastleProvider());

		String certFilePath = TestECCCertificate.class.getClassLoader().getResource("ecc_auth.key").getFile();

		ECCCertificate ecc = ECCCertificate.load(certFilePath, "test");

		String data = "test";
		String encdata = ecc.encrypt(data);
		String result = ecc.decrypt(encdata);
		Assertions.assertEquals(data, result);

		String signature = ecc.sign(data);
		boolean ok = ecc.verify(data, signature);
		Assertions.assertTrue(ok);
	}
}
