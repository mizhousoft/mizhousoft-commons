package com.mizhousoft.commons.crypto.generator;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.commons.crypto.CryptoException;
import com.mizhousoft.commons.lang.CharEncoding;

/**
 * PBKDF2密码生成器
 *
 * @version
 */
public final class PBEPasswdGenerator
{
	// 默认迭代次数
	private static final int DEFAULT_ITERATION = 10000;

	// 密码长度
	private static final int PASSWD_SIZE = 64;

	/**
	 * 构造函数
	 *
	 */
	private PBEPasswdGenerator()
	{

	}

	/**
	 * 导出密码
	 * 
	 * @param rawPwd
	 * @param salt
	 * @return
	 * @throws CryptoException
	 */
	public static String derivePasswd(String rawPwd, String salt) throws CryptoException
	{
		if (StringUtils.isBlank(rawPwd))
		{
			throw new CryptoException("Password value is null.");
		}

		if (StringUtils.isBlank(salt))
		{
			throw new CryptoException("Salt value is null.");
		}

		if (salt.length() < 16)
		{
			throw new CryptoException("Salt length is illegal.");
		}

		char[] passBytes = rawPwd.toCharArray();
		byte[] saltBytes = salt.getBytes(CharEncoding.UTF8);
		int iterations = DEFAULT_ITERATION;

		byte[] pwdBytes = PBEGenerator.deriveKey(PASSWD_SIZE, passBytes, saltBytes, iterations);
		String passwd = Base64.encodeBase64String(pwdBytes);
		return passwd;
	}
}
