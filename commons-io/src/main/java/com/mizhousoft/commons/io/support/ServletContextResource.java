package com.mizhousoft.commons.io.support;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.mizhousoft.commons.io.Resource;
import com.mizhousoft.commons.io.util.FilePathUtils;

import jakarta.servlet.ServletContext;

/**
 * ServletContext Resource
 * 
 * @version
 */
public class ServletContextResource extends Resource
{
	// web ServletContext
	private final ServletContext servletContext;

	// 路径
	private final String path;

	/**
	 * 构造函数
	 *
	 * @param servletContext
	 * @param path
	 */
	public ServletContextResource(ServletContext servletContext, String path)
	{
		this.servletContext = servletContext;

		String pathToUse = FilePathUtils.cleanPath(path);
		if (!pathToUse.startsWith("/"))
		{
			pathToUse = "/" + pathToUse;
		}
		this.path = pathToUse;
	}

	/**
	 * 获取InputStream
	 * 
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException
	{
		InputStream is = this.servletContext.getResourceAsStream(this.path);
		if (is == null)
		{
			throw new FileNotFoundException("Could not open " + path);
		}

		return is;
	}
}
