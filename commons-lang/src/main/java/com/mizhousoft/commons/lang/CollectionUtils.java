package com.mizhousoft.commons.lang;

import java.util.Collection;

/**
 * 集合工具类
 *
 * @version
 */
public abstract class CollectionUtils
{
	/**
	 * 是否为空
	 * 
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(final Collection<?> collection)
	{
		return collection == null || collection.isEmpty();
	}

	/**
	 * 把对象加入到集合中
	 * 
	 * @param <T>
	 * @param collection
	 * @param object
	 * @return
	 */
	public static <T> boolean addIgnoreNull(final Collection<T> collection, final T object)
	{
		if (collection == null)
		{
			throw new NullPointerException("The collection must not be null");
		}

		return object != null && collection.add(object);
	}
}
