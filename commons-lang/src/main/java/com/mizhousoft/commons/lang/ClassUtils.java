package com.mizhousoft.commons.lang;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class Utils
 * 
 * @version
 */
public abstract class ClassUtils
{
	private static final Logger LOG = LoggerFactory.getLogger(ClassUtils.class);

	// 公共类缓存
	private static final Map<String, Class<?>> classCache = new HashMap<String, Class<?>>(32);

	/**
	 * 获取默认的类加载器
	 * 
	 * @return
	 */
	public static ClassLoader getDefaultClassLoader()
	{
		ClassLoader cl = null;

		try
		{
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex)
		{
			// Cannot access thread context ClassLoader - falling back to system class loader...
			LOG.error(ex.getMessage(), ex);
		}

		if (cl == null)
		{
			// No thread context class loader -> use class loader of this class.
			cl = ClassUtils.class.getClassLoader();
		}

		return cl;
	}

	/**
	 * 获取类所有属性
	 * 
	 * @param clazz
	 * @return
	 */
	public static Map<String, Class<?>> getClassFields(Class<?> clazz)
	{
		Map<String, Class<?>> fieldMap = new HashMap<String, Class<?>>();

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields)
		{
			fieldMap.put(field.getName(), field.getType());
		}

		if (clazz.getSuperclass() == null)
		{
			return fieldMap;
		}

		Map<String, Class<?>> superFiledMap = getClassFields(clazz.getSuperclass());
		fieldMap.putAll(superFiledMap);

		return fieldMap;
	}

	/**
	 * 加载类
	 * 
	 * @param name
	 * @param classLoader
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Class<?> forName(String name, ClassLoader classLoader) throws ClassNotFoundException
	{
		Class<?> clazz = classCache.get(name);
		if (clazz != null)
		{
			return clazz;
		}

		ClassLoader clToUse = classLoader;
		if (clToUse == null)
		{
			clToUse = getDefaultClassLoader();
		}

		try
		{
			clazz = (clToUse != null ? clToUse.loadClass(name) : Class.forName(name));
			classCache.put(name, clazz);

			return clazz;
		}
		catch (ClassNotFoundException ex)
		{
			throw ex;
		}
	}

	/**
	 * 创建新类
	 * 
	 * @param name
	 * @param classLoader
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public static Object newInstance(String name, ClassLoader classLoader) throws InstantiationException, IllegalAccessException,
	        ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		return newInstance(forName(name, classLoader));
	}

	/**
	 * 创建新类
	 * 
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public static Object newInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
	        InvocationTargetException, NoSuchMethodException, SecurityException
	{
		if (clazz == null)
		{
			String msg = "Class method parameter cannot be null.";
			throw new IllegalArgumentException(msg);
		}

		return clazz.getDeclaredConstructor().newInstance();
	}
}
