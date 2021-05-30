package com.mizhousoft.commons.ecc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;

import org.apache.commons.codec.DecoderException;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.commons.crypto.CryptoException;
import com.mizhousoft.commons.lang.CharEncoding;
import com.mizhousoft.commons.lang.HexUtils;

/**
 * ECC证书
 *
 * @version
 */
public class ECCCertificate
{
	private static final Logger LOG = LoggerFactory.getLogger(ECCCertificate.class);

	private static final String PROVIDER = "BC";

	private PrivateKey privKey;

	private PublicKey pubKey;

	public static ECCCertificate load(InputStream istream, String password) throws CryptoException
	{
		return doLoad(new InputStreamReader(istream), password);
	}

	public static ECCCertificate load(String certFilePath, String password) throws CryptoException
	{
		try
		{
			return doLoad(new FileReader(certFilePath), password);
		}
		catch (FileNotFoundException e)
		{
			throw new CryptoException("Certificate file not found.");
		}
	}

	private static ECCCertificate doLoad(Reader reader, String password) throws CryptoException
	{
		PEMParser pr = null;

		try
		{
			JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider(PROVIDER);
			PEMDecryptorProvider decProv = new JcePEMDecryptorProviderBuilder().setProvider(PROVIDER)
			        .build(password.toCharArray());

			pr = new PEMParser(reader);
			Object o = pr.readObject();
			if (o == null || !((o instanceof PEMKeyPair) || (o instanceof PEMEncryptedKeyPair)))
			{
				throw new CryptoException("Certificate file is wrong.");
			}

			KeyPair kp = (o instanceof PEMEncryptedKeyPair)
			        ? converter.getKeyPair(((PEMEncryptedKeyPair) o).decryptKeyPair(decProv))
			        : converter.getKeyPair((PEMKeyPair) o);

			PrivateKey privKey = kp.getPrivate();
			PublicKey pubKey = kp.getPublic();

			ECCCertificate ecc = new ECCCertificate();
			ecc.privKey = privKey;
			ecc.pubKey = pubKey;

			return ecc;
		}
		catch (CryptoException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new CryptoException("Load certificate file failed.");
		}
		finally
		{
			if (null != pr)
			{
				try
				{
					pr.close();
				}
				catch (IOException e)
				{
					LOG.error("Close PEMParser failed.", e);
				}
			}
		}
	}

	public String sign(String data) throws CryptoException
	{
		try
		{
			Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", PROVIDER);
			ecdsaSign.initSign(privKey);
			ecdsaSign.update(data.getBytes(CharEncoding.UTF8));
			byte[] signature = ecdsaSign.sign();

			return HexUtils.encodeHexString(signature, false);
		}
		catch (Exception e)
		{
			throw new CryptoException("Sign data failed.");
		}
	}

	public boolean verify(String data, String signature) throws CryptoException
	{
		try
		{
			Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", PROVIDER);
			ecdsaVerify.initVerify(pubKey);
			ecdsaVerify.update(data.getBytes(CharEncoding.UTF8));
			boolean result = ecdsaVerify.verify(HexUtils.decodeHex(signature));

			return result;
		}
		catch (Exception e)
		{
			throw new CryptoException("Encrypt data failed.");
		}
	}

	public String encrypt(String data) throws CryptoException
	{
		byte[] result = encrypt(data.getBytes(CharEncoding.UTF8));
		return HexUtils.encodeHexString(result, false);
	}

	public byte[] encrypt(byte[] data) throws CryptoException
	{
		try
		{
			Cipher cipher = Cipher.getInstance("ECIES", PROVIDER);
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);

			return cipher.doFinal(data);
		}
		catch (Exception e)
		{
			throw new CryptoException("Encrypt data failed.");
		}
	}

	public String decrypt(String data) throws CryptoException
	{
		try
		{
			byte[] result = decrypt(HexUtils.decodeHex(data));
			return new String(result, CharEncoding.UTF8);
		}
		catch (DecoderException e)
		{
			throw new CryptoException("Decrypt data failed.");
		}
	}

	public byte[] decrypt(byte[] data) throws CryptoException
	{
		try
		{
			Cipher cipher = Cipher.getInstance("ECIES", PROVIDER);
			cipher.init(Cipher.DECRYPT_MODE, privKey);

			return cipher.doFinal(data);
		}
		catch (Exception e)
		{
			throw new CryptoException("Decrypt data failed.");
		}
	}
}
