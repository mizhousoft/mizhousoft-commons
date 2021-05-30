package com.mizhousoft.commons.io.support;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.mizhousoft.commons.io.Resource;
import com.mizhousoft.commons.io.util.FilePathUtils;
import com.mizhousoft.commons.lang.ClassUtils;

/**
 * ClassPath Resource
 * 
 * @version
 */
public class ClassPathResource extends Resource
{
	// File Path
	private final String path;

	// ClassLoader
	private ClassLoader classLoader;

	// Class
	private Class<?> clazz;

	/**
	 * 构造函数
	 * 
	 * @param path
	 */
	public ClassPathResource(String path)
	{
		this(path, (ClassLoader) null);
	}

	/**
	 * 构造函数
	 * 
	 * @param path
	 * @param classLoader
	 */
	public ClassPathResource(String path, ClassLoader classLoader)
	{
		this.path = FilePathUtils.cleanPath(path);
		this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
	}

	/**
	 * 构造函数
	 * 
	 * @param path
	 * @param clazz
	 */
	public ClassPathResource(String path, Class<?> clazz)
	{
		this.path = FilePathUtils.cleanPath(path);
		this.clazz = clazz;
	}

	/**
	 * 构造函数
	 * 
	 * @param path
	 * @param classLoader
	 * @param clazz
	 */
	protected ClassPathResource(String path, ClassLoader classLoader, Class<?> clazz)
	{
		this.path = FilePathUtils.cleanPath(path);
		this.classLoader = classLoader;
		this.clazz = clazz;
	}

	/**
	 * 获取InputStream
	 * 
	 * @return
	 * @throws IOException
	 */
	public InputStream getInputStream() throws IOException
	{
		InputStream is;

		if (this.clazz != null)
		{
			is = this.clazz.getResourceAsStream(this.path);
		}
		else
		{
			is = this.classLoader.getResourceAsStream(this.path);
		}

		if (is == null)
		{
			throw new FileNotFoundException(path + " cannot be opened because it does not exist");
		}

		return is;
	}
}
