package com.mizhousoft.commons.lang;

import java.awt.Color;
import java.awt.Font;

import org.junit.Assert;
import org.junit.Test;

/**
 * WaterMarkUtils Test
 *
 * @version
 */
public class WaterMarkUtilsTest
{
	@Test
	public void markYYZZ()
	{
		try
		{
			String srcPath = "C:\\\\work\\\\test.jpg";
			String destPath = "C:\\\\work\\\\test1.jpg";
			Font font = new Font("宋体", Font.PLAIN, 80);
			Color color = Color.red;
			String text = "仅用于转让米舟科技商标至深圳米舟科技有限公司";
			int coordX = 600;
			int coordY = 100;
			Integer degree = 120;
			float alpha = 0.2F;
			WaterMarkUtils.mark(srcPath, destPath, font, color, text, coordX, coordY, degree, alpha);

			srcPath = "C:\\\\work\\\\test1.jpg";
			destPath = "C:\\\\work\\\\test2.jpg";
			coordX = 800;
			coordY = 400;
			degree = 120;
			WaterMarkUtils.mark(srcPath, destPath, font, color, text, coordX, coordY, degree, alpha);

			srcPath = "C:\\\\work\\\\test2.jpg";
			destPath = "C:\\\\work\\\\test3.jpg";
			coordX = 100;
			coordY = 200;
			degree = 90;
			alpha = 0.04F;
			WaterMarkUtils.mark(srcPath, destPath, font, color, text, coordX, coordY, degree, alpha);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void markCardZ()
	{
		try
		{
			String srcPath = "C:\\\\work\\\\card-z.jpg";
			String destPath = "C:\\\\work\\\\card-z-1.jpg";
			Font font = new Font("宋体", Font.PLAIN, 80);
			Color color = Color.red;
			String text = "仅用于申请微信开放平台";
			int coordX = 140;
			int coordY = 500;
			Integer degree = 30;
			float alpha = 0.2F;
			WaterMarkUtils.mark(srcPath, destPath, font, color, text, coordX, coordY, degree, alpha);

			srcPath = "C:\\\\work\\\\card-z-1.jpg";
			destPath = "C:\\\\work\\\\card-z-2.jpg";
			coordX = 140;
			coordY = 700;
			degree = 30;
			WaterMarkUtils.mark(srcPath, destPath, font, color, text, coordX, coordY, degree, alpha);

			srcPath = "C:\\\\work\\\\card-z-2.jpg";
			destPath = "C:\\\\work\\\\card-z-3.jpg";
			coordX = 100;
			coordY = 800;
			degree = 0;
			alpha = 0.05F;
			WaterMarkUtils.mark(srcPath, destPath, font, color, text, coordX, coordY, degree, alpha);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void markCardF()
	{
		try
		{
			String srcPath = "C:\\\\work\\\\card-f.jpg";
			String destPath = "C:\\\\work\\\\card-f-1.jpg";
			Font font = new Font("宋体", Font.PLAIN, 80);
			Color color = Color.red;
			String text = "仅用于申请微信开放平台";
			int coordX = 140;
			int coordY = 500;
			Integer degree = 30;
			float alpha = 0.2F;
			WaterMarkUtils.mark(srcPath, destPath, font, color, text, coordX, coordY, degree, alpha);

			srcPath = "C:\\\\work\\\\card-f-1.jpg";
			destPath = "C:\\\\work\\\\card-f-2.jpg";
			coordX = 140;
			coordY = 700;
			degree = 30;
			WaterMarkUtils.mark(srcPath, destPath, font, color, text, coordX, coordY, degree, alpha);

			srcPath = "C:\\\\work\\\\card-f-2.jpg";
			destPath = "C:\\\\work\\\\card-f-3.jpg";
			coordX = 100;
			coordY = 800;
			degree = 0;
			alpha = 0.05F;
			WaterMarkUtils.mark(srcPath, destPath, font, color, text, coordX, coordY, degree, alpha);
		}
		catch (Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
