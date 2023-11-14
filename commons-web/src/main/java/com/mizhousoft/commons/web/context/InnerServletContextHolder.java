package com.mizhousoft.commons.web.context;

import jakarta.servlet.ServletContext;

/**
 * ServletContext容器
 *
 * @version
 */
abstract class InnerServletContextHolder
{
	// ServletContext
	private static ServletContext servletContext;

	/**
	 * 获取servletContext
	 * 
	 * @return
	 */
	public static ServletContext getServletContext()
	{
		return servletContext;
	}

	/**
	 * 设置servletContext
	 * 
	 * @param servletContext
	 */
	public synchronized static void setServletContext(ServletContext servletContext)
	{
		InnerServletContextHolder.servletContext = servletContext;
	}
}
