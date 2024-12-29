package com.mizhousoft.commons.lang;

import java.util.Collections;
import java.util.HashSet;
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

	/**
	 * 合并集合
	 * 
	 * @param <T>
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> Set<T> merge(final Set<? extends T> a, final Set<? extends T> b)
	{
		Set<T> set = new HashSet<>(a.size() + b.size());

		set.addAll(a);
		set.addAll(b);

		return set;
	}

	/**
	 * 合并集合
	 * 
	 * @param <T>
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static <T> Set<T> merge(final Set<? extends T> a, final Set<? extends T> b, final Set<? extends T> c)
	{
		Set<T> set = new HashSet<>(a.size() + b.size() + c.size());

		set.addAll(a);
		set.addAll(b);
		set.addAll(c);

		return set;
	}

	/**
	 * 合并集合
	 * 
	 * @param <T>
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	public static <T> Set<T> merge(final Set<? extends T> a, final Set<? extends T> b, final Set<? extends T> c, final Set<? extends T> d)
	{
		Set<T> set = new HashSet<>(a.size() + b.size() + c.size() + d.size());

		set.addAll(a);
		set.addAll(b);
		set.addAll(c);
		set.addAll(d);

		return set;
	}
}
