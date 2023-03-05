package com.mizhousoft.commons.io.zip;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.io.ZipException;

/**
 * ZipCompressUtils Test
 *
 * @version
 */
public class TestZipCompressUtils
{
	@Test
	public void compress()
	{
		try
		{
			ZipCompressUtils.compress("C:\\\\work\\\\test\\ant.zip", "C:\\work\\test\\main.go", true);
		}
		catch (ZipException e)
		{
			Assertions.fail(e);
		}
	}
}
