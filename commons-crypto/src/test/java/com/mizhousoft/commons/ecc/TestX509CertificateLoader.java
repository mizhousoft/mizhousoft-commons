package com.mizhousoft.commons.ecc;

import java.security.Security;

import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.crypto.CryptoException;

/**
 * X509CertificateLoader
 *
 * @version
 */
public class TestX509CertificateLoader
{
	@Test
	public void test() throws CryptoException
	{
		Security.addProvider(new BouncyCastleProvider());

		String certFilePath = TestECCCertificate.class.getClassLoader().getResource("ecc_auth.cer").getFile();
		ECPublicKey publicKey = X509CertificateLoader.load(certFilePath);
		Assertions.assertNotNull(publicKey);

		certFilePath = TestECCCertificate.class.getClassLoader().getResource("ecc_ca.cer").getFile();
		publicKey = X509CertificateLoader.load(certFilePath);
		Assertions.assertNotNull(publicKey);
	}
}
