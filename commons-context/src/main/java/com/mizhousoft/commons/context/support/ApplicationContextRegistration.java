package com.mizhousoft.commons.context.support;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.context.ApplicationContext;

import com.mizhousoft.commons.context.ServiceNotFoundException;

/**
 * ApplicationContext Registration
 * 
 * @version
 */
public abstract class ApplicationContextRegistration
{
	private static final List<ApplicationContext> applicationContexts = new CopyOnWriteArrayList<ApplicationContext>();

	/**
	 * 注册应用上下文
	 * 
	 * @param applicationContext
	 */
	public static void registerApplicationContext(ApplicationContext applicationContext)
	{
		applicationContexts.add(applicationContext);
	}

	/**
	 * 注销应用上下文
	 * 
	 * @param applicationContext
	 */
	public static void unregisterApplicationContext(ApplicationContext applicationContext)
	{
		applicationContexts.remove(applicationContext);
	}

	/**
	 * 获取Application Context Bean
	 * 
	 * @param name
	 * @return
	 */
	public static Object getBean(String name)
	{
		Iterator<ApplicationContext> iter = applicationContexts.iterator();
		while (iter.hasNext())
		{
			ApplicationContext applicationContext = iter.next();

			try
			{
				return applicationContext.getBean(name);
			}
			catch (Throwable e)
			{
				// ignore
			}
		}

		throw new ServiceNotFoundException(name + " service not found.");
	}

	/**
	 * 获取Application Context Bean
	 * 
	 * @param <T>
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz)
	{
		Iterator<ApplicationContext> iter = applicationContexts.iterator();
		while (iter.hasNext())
		{
			ApplicationContext applicationContext = iter.next();

			try
			{
				return applicationContext.getBean(clazz);
			}
			catch (Throwable e)
			{
				// ignore
			}
		}

		throw new ServiceNotFoundException(clazz.getName() + " service not found.");
	}
}
