package com.mizhousoft.commons.lang;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Map工具类
 *
 * @version
 */
public abstract class MapUtils
{
	/**
	 * 是否为空
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isEmpty(final Map<K, V> map)
	{
		return map == null || map.isEmpty();
	}

	/**
	 * 如果Map为null，返回空Map
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public static <K, V> Map<K, V> emptyIfNull(final Map<K, V> map)
	{
		return map == null ? Collections.<K, V> emptyMap() : map;
	}

	/**
	 * 如果为null，返回默认Map
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @param defaultMap
	 * @return
	 */
	public static <K, V> Map<K, V> defaultIfNull(final Map<K, V> map, final Map<K, V> defaultMap)
	{
		return map == null ? defaultMap : map;
	}

	/**
	 * 合并Map
	 * 
	 * @param <K>
	 * @param <V>
	 * @param a
	 * @param b
	 * @return
	 */
	public static <K, V> Map<K, V> merge(final Map<K, V> a, final Map<K, V> b)
	{
		Map<K, V> map = new HashMap<>(a.size() + b.size());

		map.putAll(a);
		map.putAll(b);

		return map;
	}

	/**
	 * 合并Map
	 * 
	 * @param <K>
	 * @param <V>
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static <K, V> Map<K, V> merge(final Map<K, V> a, final Map<K, V> b, final Map<K, V> c)
	{
		Map<K, V> map = new HashMap<>(a.size() + b.size());

		map.putAll(a);
		map.putAll(b);
		map.putAll(c);

		return map;
	}

	/**
	 * 合并Map
	 * 
	 * @param <K>
	 * @param <V>
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	public static <K, V> Map<K, V> merge(final Map<K, V> a, final Map<K, V> b, final Map<K, V> c, final Map<K, V> d)
	{
		Map<K, V> map = new HashMap<>(a.size() + b.size());

		map.putAll(a);
		map.putAll(b);
		map.putAll(c);
		map.putAll(d);

		return map;
	}
}
