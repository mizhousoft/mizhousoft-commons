package com.mizhousoft.commons.web.i18n.domain;

import java.io.IOException;
import java.io.InputStream;

/**
 * ErrorCode Message ResourceBundle
 * 
 * @version
 */
public class ErrorCodeMessageResourceBundle extends MessageResourceBundle
{
	/**
	 * 构造函数
	 * 
	 * @param stream
	 * @throws IOException
	 */
	public ErrorCodeMessageResourceBundle(InputStream stream) throws IOException
	{
		super(stream);
	}
}