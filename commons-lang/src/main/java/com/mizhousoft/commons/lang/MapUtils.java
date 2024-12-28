package com.mizhousoft.commons.lang;

import java.util.Collections;
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
}
