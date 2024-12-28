package com.mizhousoft.commons.lang;

import java.util.Collections;
import java.util.Set;

/**
 * 集合工具类
 *
 * @version
 */
public abstract class SetUtils
{
	/**
	 * 如果为null，返回空集合
	 * 
	 * @param <T>
	 * @param set
	 * @return
	 */
	public static <T> Set<T> emptyIfNull(final Set<T> set)
	{
		return set == null ? Collections.<T> emptySet() : set;
	}

	/**
	 * 如果为null，返回默认集合
	 * 
	 * @param <T>
	 * @param set
	 * @param defaultSet
	 * @return
	 */
	public static <T> Set<T> defaultIfNull(final Set<T> set, final Set<T> defaultSet)
	{
		return set == null ? defaultSet : set;
	}
}
