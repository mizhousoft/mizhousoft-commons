package com.mizhousoft.commons.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * InputStream Source
 * 
 * @version
 */
public interface InputStreamSource
{
	/**
	 * 获取InputStream
	 * 
	 * @return
	 * @throws IOException
	 */
	InputStream getInputStream() throws IOException;
}
