package com.mizhousoft.commons.io.util;

/**
 * 文件工具类
 *
 * @version
 */
public abstract class FilenameUtils
{
	/**
	 * 判断路径是否绝对路径
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isAbsolutePath(String path)
	{
		if (null == path)
		{
			return false;
		}

		int length = path.length();
		for (int i = 0; i < length; ++i)
		{
			char a = path.charAt(i);
			if (a == '.')
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断文件路径是否绝对路径
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isAbsoluteFilePath(String filePath)
	{
		if (null == filePath || -1 == filePath.lastIndexOf("."))
		{
			return false;
		}

		int index = filePath.lastIndexOf(".");
		String path = filePath.substring(0, index);

		return isAbsolutePath(path);
	}
}
