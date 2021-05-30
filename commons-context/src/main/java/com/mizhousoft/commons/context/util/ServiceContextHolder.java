package com.mizhousoft.commons.context.util;

import com.mizhousoft.commons.context.support.ApplicationContextRegistration;

/**
 * ContextHolder
 * 
 * @version
 */
public class ServiceContextHolder
{
	/**
	 * 获取服务
	 * 
	 * @param name
	 * @return
	 */
	public static Object getService(String name)
	{
		return ApplicationContextRegistration.getBean(name);
	}

	/**
	 * 获取服务
	 * 
	 * @param <T>
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getService(Class<T> clazz)
	{
		return ApplicationContextRegistration.getBean(clazz);
	}
}