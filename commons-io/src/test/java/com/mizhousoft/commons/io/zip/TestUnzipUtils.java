package com.mizhousoft.commons.io.zip;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.mizhousoft.commons.io.ZipException;

/**
 * UnzipUtils测试类
 *
 * @version
 */
public class TestUnzipUtils
{
	@Test
	public void testValidZip()
	{
		try
		{
			String filePath = TestUnzipUtils.class.getClassLoader().getResource("test.zip").getFile();
			File file = new File(filePath);

			ZipUtils.checkZip(file);
		}
		catch (ZipException e)
		{
			Assertions.fail(e);
		}
	}

	@Test
	public void testInvalidZip()
	{
		try
		{
			String filePath = TestUnzipUtils.class.getClassLoader().getResource("testerror.zip").getFile();
			File file = new File(filePath);

			ZipUtils.checkZip(file);

			Assertions.fail();
		}
		catch (ZipException e)
		{
			Assertions.assertTrue(true);
		}
	}
}
