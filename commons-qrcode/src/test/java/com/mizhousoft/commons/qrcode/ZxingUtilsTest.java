package com.mizhousoft.commons.qrcode;

import java.io.File;

/**
 * ZxingUtils Test
 *
 * @version
 */
public class ZxingUtilsTest
{
	public static void main(String[] args) throws QrcodeException
	{
		String logoPath = ZxingUtilsTest.class.getClassLoader().getResource("logo.png").getFile();
		String imgPath = ZxingUtilsTest.class.getClassLoader().getResource("poster.jpg").getFile();

		String content = "https://dev.mizhousoft.com/mpapi/spread/index.action?userId=74";
		QrcodeParam codeParam = new QrcodeLogoParam(content, logoPath);

		File file = new File(logoPath);
		String parent = file.getParent();

		ZxingUtils.genQRCode(codeParam, parent + File.separator + "test-logo.png");

		codeParam = new QrcodeParam(content);
		ZxingUtils.genQRCode(codeParam, parent + File.separator + "test.png");

		codeParam = new QrcodeImgMergeParam(content, imgPath, 340, 1190);
		codeParam.setWidth(400);
		codeParam.setHeight(400);
		ZxingUtils.genQRCode(codeParam, parent + File.separator + "test-img.jpg");
	}
}
