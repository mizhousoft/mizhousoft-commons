package com.mizhousoft.commons.qrcode;

import java.io.File;

import org.junit.Test;

/**
 * WifiPasswordGenerator
 *
 * @version
 */
public class WifiPasswordGeneratorTest
{
	@Test
	public void test() throws QrcodeException
	{
		String filePath = "";
		String content = "";

		QrcodeParam codeParam = new QrcodeParam(content);

		ZxingUtils.genQRCode(codeParam, filePath + File.separator + "wifi.png");
	}
}
