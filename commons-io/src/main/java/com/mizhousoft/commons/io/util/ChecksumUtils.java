package com.mizhousoft.commons.io.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 完整性校验
 *
 * @version
 */
public abstract class ChecksumUtils
{
	/**
	 * 校验checksum
	 * 
	 * @param file
	 * @param testChecksum
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static boolean verifyChecksumSHA256(File file, String testChecksum)
	        throws NoSuchAlgorithmException, IOException
	{
		String fileHash = checksumSHA256(file);

		return fileHash.equalsIgnoreCase(testChecksum);
	}

	/**
	 * 校验checksum
	 * 
	 * @param istream
	 * @param testChecksum
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static boolean verifyChecksumSHA256(InputStream istream, String testChecksum)
	        throws NoSuchAlgorithmException, IOException
	{
		String fileHash = checksumSHA256(istream);

		return fileHash.equalsIgnoreCase(testChecksum);
	}

	/**
	 * sha256 checksum
	 * 
	 * @param file
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String checksumSHA256(File file) throws NoSuchAlgorithmException, IOException
	{
		try (InputStream istream = new FileInputStream(file))
		{
			String fileHash = checksumSHA256(istream);
			return fileHash;
		}
	}

	/**
	 * sha256 checksum
	 * 
	 * @param istream
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String checksumSHA256(InputStream istream) throws NoSuchAlgorithmException, IOException
	{
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

		byte[] data = new byte[4096];
		int read = istream.read(data);
		while (read != -1)
		{
			sha256.update(data, 0, read);
			read = istream.read(data);
		}

		byte[] hashBytes = sha256.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < hashBytes.length; i++)
		{
			sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		String fileHash = sb.toString();
		return fileHash;
	}
}
