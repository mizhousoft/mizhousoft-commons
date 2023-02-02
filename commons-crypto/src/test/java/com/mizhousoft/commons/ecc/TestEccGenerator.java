package com.mizhousoft.commons.ecc;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.crypto.CryptoException;

/**
 * EccGenerator
 *
 * @version
 */
public class TestEccGenerator
{
	@Test
	public void test() throws CryptoException
	{
		Security.addProvider(new BouncyCastleProvider());

		KeyPair keyPair = EccGenerator.genKeyPair();

		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();

		String priv = EccGenerator.encodePrivateKey(privateKey);
		String pub = EccGenerator.encodePublicKey(publicKey);
		System.out.println(priv);
		System.out.println(pub);

		PrivateKey d = EccGenerator.decodePrivateKey(priv);
		PublicKey k = EccGenerator.decodePublicKey(pub);

		Assertions.assertNotNull(d);
		Assertions.assertNotNull(k);
	}
}
