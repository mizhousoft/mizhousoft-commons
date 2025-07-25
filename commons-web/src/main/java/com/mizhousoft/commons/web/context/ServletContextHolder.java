package com.mizhousoft.commons.web.context;

import java.io.File;

import org.apache.commons.lang3.Strings;

/**
 * ServletContext工具类
 *
 * @version
 */
public abstract class ServletContextHolder
{
	/**
	 * 获取上下文路径
	 * 
	 * @return
	 */
	public static String getContextPath()
	{
		return InnerServletContextHolder.getServletContext().getContextPath();
	}

	/**
	 * 获取真实路径
	 * 
	 * @param withEndSeparator
	 * @return
	 */
	public static String getRealPath(boolean withEndSeparator)
	{
		String path = InnerServletContextHolder.getServletContext().getRealPath("/");
		if (withEndSeparator)
		{
			if (!Strings.CS.endsWith(path, File.separator))
			{
				path = path + File.separator;
			}
		}
		else
		{
			if (Strings.CS.endsWith(path, File.separator))
			{
				path = path.substring(0, path.length() - 1);
			}
		}

		return path;
	}
}
