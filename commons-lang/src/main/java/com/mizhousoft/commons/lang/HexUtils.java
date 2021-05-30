package com.mizhousoft.commons.lang;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * Hex工具类
 *
 * @version
 */
public final class HexUtils
{
	/**
	 * 转码
	 * 
	 * @param data
	 * @param toLowerCase
	 * @return
	 */
	public static String encodeHexString(final byte[] data, final boolean toLowerCase)
	{
		return new String(Hex.encodeHex(data, toLowerCase));
	}

	/**
	 * 转码
	 * 
	 * @param data
	 * @return
	 * @throws DecoderException
	 */
	public static byte[] decodeHex(String data) throws DecoderException
	{
		return Hex.decodeHex(data.toCharArray());
	}
}
