package com.mizhousoft.commons.io.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.commons.lang.CharEncoding;

/**
 * GZip压缩工具
 *
 */
public abstract class GZipCompressUtils
{
	public static String compressToBase64String(String data) throws IOException
	{
		if (StringUtils.isBlank(data))
		{
			return null;
		}

		byte[] bytes = compress(data);

		String result = Base64.encodeBase64String(bytes);

		return result;
	}

	public static byte[] compress(String data) throws IOException
	{
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream())
		{
			try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream))
			{
				gzipOutputStream.write(data.getBytes(CharEncoding.UTF8));
			}

			return byteArrayOutputStream.toByteArray();
		}
	}

	public static String decompressBase64String(String compressedBase64) throws IOException
	{
		if (StringUtils.isBlank(compressedBase64))
		{
			return null;
		}

		byte[] bytes = Base64.decodeBase64(compressedBase64);

		return decompress(bytes);
	}

	public static String decompress(byte[] compressedData) throws IOException
	{
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedData);
		        GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream))
		{
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			int length;
			while ((length = gzipInputStream.read(buffer)) > 0)
			{
				byteArrayOutputStream.write(buffer, 0, length);
			}

			return new String(byteArrayOutputStream.toByteArray(), CharEncoding.UTF8);
		}
	}
}
