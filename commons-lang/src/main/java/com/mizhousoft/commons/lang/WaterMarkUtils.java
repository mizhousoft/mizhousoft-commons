package com.mizhousoft.commons.lang;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mizhousoft.commons.data.NestedException;

/**
 * 水印工具类
 *
 * @version
 */
public abstract class WaterMarkUtils
{
	public static void mark(String srcPath, String destPath, Font font, Color color, String text, int coordX, int coordY, Integer degree,
	        float alpha) throws NestedException
	{
		try
		{
			File srcImgFile = new File(srcPath);
			Image srcImage = ImageIO.read(srcImgFile);

			int srcImgWidth = srcImage.getWidth(null);
			int srcImgHeight = srcImage.getHeight(null);

			// 加水印
			BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufImg.createGraphics();

			g.drawImage(srcImage, 0, 0, srcImgWidth, srcImgHeight, null);

			g.setColor(color);
			g.setFont(font);
			g.rotate(Math.toRadians(degree), coordX, coordY);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawString(text, coordX, coordY);

			g.dispose();

			FileOutputStream outImgStream = new FileOutputStream(destPath);
			ImageIO.write(bufImg, "jpg", outImgStream);
			outImgStream.flush();
			outImgStream.close();
		}
		catch (IOException e)
		{
			throw new NestedException("Add water mark failed.", e);
		}
	}

	/**
	 * 获取水印文字总长度
	 * 
	 * @param text
	 * @param g
	 * @return
	 */
	public static int getWatermarkLength(String text, Graphics2D g)
	{
		return g.getFontMetrics(g.getFont()).charsWidth(text.toCharArray(), 0, text.length());
	}
}
