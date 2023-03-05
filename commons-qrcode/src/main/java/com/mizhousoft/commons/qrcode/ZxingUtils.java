package com.mizhousoft.commons.qrcode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.mizhousoft.commons.lang.CharEncoding;

/**
 * Zxing工具类
 *
 * @version
 */
public abstract class ZxingUtils
{
	// 图像类型
	private static String PNG_FORMAT = "png";

	private static String JPG_FORMAT = "jpg";

	public static void genQRCode(QrcodeParam codeParam, String destPath) throws QrcodeException
	{
		File destFile = new File(destPath);

		genQRCode(codeParam, destFile);
	}

	public static void genQRCode(QrcodeParam codeParam, File destFile) throws QrcodeException
	{
		try
		{
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>(3);
			hints.put(EncodeHintType.CHARACTER_SET, CharEncoding.UTF8_NAME);
			hints.put(EncodeHintType.ERROR_CORRECTION, codeParam.getLevel());
			hints.put(EncodeHintType.MARGIN, codeParam.getMargin());

			int width = codeParam.getWidth();
			int height = codeParam.getHeight();
			String content = codeParam.getContent();

			// 生成矩阵
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

			int onColor = codeParam.getOnColor();
			int offColor = codeParam.getOffColor();

			MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);

			if (codeParam instanceof QrcodeLogoParam)
			{
				writeToFileWithLogo((QrcodeLogoParam) codeParam, bitMatrix, destFile);
			}
			else if (codeParam instanceof QrcodeImgMergeParam)
			{
				writeToFileWithImg((QrcodeImgMergeParam) codeParam, bitMatrix, destFile);
			}
			else
			{
				// 直接输出图像
				MatrixToImageWriter.writeToPath(bitMatrix, PNG_FORMAT, destFile.toPath(), config);
			}
		}
		catch (WriterException | IOException e)
		{
			throw new QrcodeException("Generate qrcode failed.", e);
		}
	}

	private static void writeToFileWithLogo(QrcodeLogoParam codeParam, BitMatrix matrix, File destFile) throws QrcodeException
	{
		Graphics2D graphics = null;
		Image logoImage = null;

		try
		{
			BufferedImage bufferImg = toBufferedImage(codeParam, matrix);
			graphics = bufferImg.createGraphics();

			int ratioWidth = bufferImg.getWidth() * 2 / 10;
			int ratioHeight = bufferImg.getHeight() * 2 / 10;

			String logoPath = codeParam.getLogoPath();

			// 载入logo
			logoImage = ImageIO.read(new File(logoPath));
			int logoWidth = logoImage.getWidth(null) > ratioWidth ? ratioWidth : logoImage.getWidth(null);
			int logoHeight = logoImage.getHeight(null) > ratioHeight ? ratioHeight : logoImage.getHeight(null);

			int x = (bufferImg.getWidth() - logoWidth) / 2;
			int y = (bufferImg.getHeight() - logoHeight) / 2;

			graphics.drawImage(logoImage, x, y, logoWidth, logoHeight, null);
			graphics.setColor(Color.black);
			graphics.setBackground(Color.WHITE);

			graphics.dispose();
			logoImage.flush();

			if (!ImageIO.write(bufferImg, PNG_FORMAT, destFile))
			{
				throw new QrcodeException("Could not write an image of format " + PNG_FORMAT + " to " + destFile);
			}
		}
		catch (IOException e)
		{
			throw new QrcodeException("Generate qrcode failed.", e);
		}
	}

	private static void writeToFileWithImg(QrcodeImgMergeParam codeParam, BitMatrix matrix, File destFile) throws QrcodeException
	{
		try
		{
			BufferedImage bufferImg = toBufferedImage(codeParam, matrix);

			BufferedImage image = ImageIO.read(new File(codeParam.getImgPath()));

			BufferedImage combinedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

			int left = codeParam.getQrcodeLeft();
			int top = codeParam.getQrcodeTop();

			// paint both images, preserving the alpha channels
			Graphics g = combinedImage.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.drawImage(bufferImg, left, top, null);

			g.dispose();

			if (!ImageIO.write(combinedImage, JPG_FORMAT, destFile))
			{
				throw new QrcodeException("Could not write an image of format " + JPG_FORMAT + " to " + destFile);
			}
		}
		catch (IOException e)
		{
			throw new QrcodeException("Generate qrcode failed.", e);
		}
	}

	private static BufferedImage toBufferedImage(QrcodeParam codeParam, BitMatrix matrix)
	{
		int width = matrix.getWidth();
		int height = matrix.getHeight();

		BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int onColor = codeParam.getOnColor();
		int offColor = codeParam.getOffColor();

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				bufferImage.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
			}
		}

		return bufferImage;
	}
}
