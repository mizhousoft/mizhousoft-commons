package com.mizhousoft.commons.io.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mizhousoft.commons.io.Resource;

/**
 * Properties加载器工具类
 * 
 * @version
 */
public class PropertiesLoaderUtils
{
	/**
	 * 加载资源
	 * 
	 * @param resource
	 * @return
	 * @throws IOException
	 */
	public static Properties loadProperties(Resource resource) throws IOException
	{
		Properties props = new Properties();
		fillProperties(props, resource);

		return props;
	}

	/**
	 * Fill the given properties from the given resource.
	 * 
	 * @param props the Properties instance to fill
	 * @param resource the resource to load from
	 * @throws IOException if loading failed
	 */
	public static void fillProperties(Properties props, Resource resource) throws IOException
	{
		InputStream is = resource.getInputStream();

		try
		{
			props.load(is);
		}
		finally
		{
			is.close();
		}
	}
}
