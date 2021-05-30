package com.mizhousoft.commons.io.zip;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

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
			Assert.fail(e.getMessage());
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

			Assert.fail();
		}
		catch (ZipException e)
		{
			Assert.assertTrue(true);
		}
	}
}
