package com.mizhousoft.commons.io.util;

/**
 * String Utils
 * 
 * @version
 */
public abstract class FilePathUtils
{
	/**
	 * 清理路径
	 * 
	 * @param path
	 * @return
	 */
	public static String cleanPath(String path)
	{
		int index = path.indexOf(":");
		if (-1 != index)
		{
			path = path.substring(index + 1);
		}

		return path;
	}
}
