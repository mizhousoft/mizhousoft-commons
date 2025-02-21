package com.mizhousoft.commons.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * HashBasedTable
 *
 */
public class HashBasedTable<R, C, V> extends AbstractTable<R, C, V>
{
	/**
	 * 构造函数
	 *
	 * @param backingMap
	 */
	public HashBasedTable(Map<R, Map<C, V>> backingMap)
	{
		super(backingMap);
	}

	/**
	 * 创建对象
	 * 
	 * @param <R>
	 * @param <C>
	 * @param <V>
	 * @param expectedRows
	 * @return
	 */
	public static <R, C, V> HashBasedTable<R, C, V> create(int expectedRows)
	{
		return new HashBasedTable<>(new LinkedHashMap<R, Map<C, V>>(expectedRows));
	}

	/**
	 * 创建对象
	 * 
	 * @param <R>
	 * @param <C>
	 * @param <V>
	 * @param table
	 * @return
	 */
	public static <R, C, V> HashBasedTable<R, C, V> create(Table<R, C, V> table)
	{
		HashBasedTable<R, C, V> result = create(10);
		result.putAll(table);

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Map<C, V> createRow()
	{
		return new LinkedHashMap<C, V>(10);
	}
}