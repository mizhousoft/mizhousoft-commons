package com.mizhousoft.commons.ecc;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.mizhousoft.commons.crypto.CryptoException;
import com.mizhousoft.commons.lang.HexUtils;

/**
 * ECC生成器
 *
 * @version
 */
public abstract class EccGenerator
{
	public static KeyPair genKeyPair() throws CryptoException
	{
		try
		{
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
			keyPairGenerator.initialize(256, new SecureRandom());
			return keyPairGenerator.generateKeyPair();
		}
		catch (Exception e)
		{
			throw new CryptoException("Generate key pair failed.");
		}
	}

	public static String encodePublicKey(PublicKey publicKey)
	{
		return HexUtils.encodeHexString(publicKey.getEncoded(), false);
	}

	public static String encodePrivateKey(PrivateKey privateKey)
	{
		return HexUtils.encodeHexString(privateKey.getEncoded(), false);
	}

	public static PublicKey decodePublicKey(String keyStr) throws CryptoException
	{
		try
		{
			byte[] keyBytes = HexUtils.decodeHex(keyStr);

			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");

			return keyFactory.generatePublic(keySpec);
		}
		catch (Exception e)
		{
			throw new CryptoException("Decode public key failed.");
		}
	}

	public static PrivateKey decodePrivateKey(String keyStr) throws CryptoException
	{
		try
		{
			byte[] keyBytes = HexUtils.decodeHex(keyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");

			return keyFactory.generatePrivate(keySpec);
		}
		catch (Exception e)
		{
			throw new CryptoException("Decode private key failed.");
		}
	}
}
