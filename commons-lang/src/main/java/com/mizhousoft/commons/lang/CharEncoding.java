package com.mizhousoft.commons.lang;

import java.nio.charset.Charset;

/**
 * 字符集
 *
 * @version
 */
public interface CharEncoding
{
	/**
	 * UTF-8
	 */
	String UTF8_NAME = "UTF-8";

	/**
	 * 字符集
	 */
	Charset UTF8 = Charset.forName(UTF8_NAME);
}
