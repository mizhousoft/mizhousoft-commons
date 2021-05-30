package com.mizhousoft.commons.ecc;

import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.openssl.PEMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.commons.crypto.CryptoException;

/**
 * 加载器
 *
 * @version
 */
public abstract class X509CertificateLoader
{
	private static final Logger LOG = LoggerFactory.getLogger(X509CertificateLoader.class);

	public static ECPublicKey load(String filePath) throws CryptoException
	{
		PEMParser pem = null;

		try
		{
			pem = new PEMParser(new FileReader(filePath));
			X509CertificateHolder holder = (X509CertificateHolder) pem.readObject();

			X509Certificate cert = new JcaX509CertificateConverter().setProvider("BC").getCertificate(holder);
			PublicKey publicKey = cert.getPublicKey();
			if (publicKey instanceof ECPublicKey)
			{
				return (ECPublicKey) publicKey;
			}
			else
			{
				throw new CryptoException("Public key is not ECPublicKey.");
			}
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
			if (null != pem)
			{
				try
				{
					pem.close();
				}
				catch (IOException e)
				{
					LOG.error("Close PEMParser failed.", e);
				}
			}
		}
	}
}
