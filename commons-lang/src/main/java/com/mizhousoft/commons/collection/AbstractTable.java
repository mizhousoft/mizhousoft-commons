package com.mizhousoft.commons.collection;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * AbstractTable
 *
 */
abstract class AbstractTable<R extends Object, C extends Object, V extends Object> implements Table<R, C, V>
{
	/**
	 * 容器
	 */
	protected final Map<R, Map<C, V>> backingMap;

	/**
	 * 构造函数
	 *
	 * @param backingMap
	 */
	public AbstractTable(Map<R, Map<C, V>> backingMap)
	{
		this.backingMap = backingMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Object rowKey, Object columnKey)
	{
		if (null == rowKey || null == columnKey)
		{
			return false;
		}

		Map<C, V> row = backingMap.get(rowKey);
		if (null == row)
		{
			return false;
		}

		return row.containsKey(columnKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsRow(Object rowKey)
	{
		if (null == rowKey)
		{
			return false;
		}

		return backingMap.containsKey(rowKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V get(Object rowKey, Object columnKey)
	{
		if (null == rowKey || null == columnKey)
		{
			return null;
		}

		Map<C, V> row = backingMap.get(rowKey);
		if (null == row)
		{
			return null;
		}

		return row.get(columnKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size()
	{
		int size = 0;
		for (Map<C, V> map : backingMap.values())
		{
			size += map.size();
		}

		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear()
	{
		backingMap.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V put(R rowKey, C columnKey, V value)
	{
		if (null == rowKey || null == columnKey || null == value)
		{
			return null;
		}

		Map<C, V> row = getOrCreateRow(rowKey);

		return row.put(columnKey, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void putAll(Table<? extends R, ? extends C, ? extends V> table)
	{
		Iterator<? extends R> keyIter = table.rowKeySet().iterator();
		while (keyIter.hasNext())
		{
			R rowKey = (R) keyIter.next();
			Map<C, V> row = row(rowKey);

			Iterator<Entry<C, V>> iter = row.entrySet().iterator();
			while (iter.hasNext())
			{
				Entry<C, V> entry = iter.next();

				put(rowKey, entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public V remove(Object rowKey, Object columnKey)
	{
		if ((rowKey == null) || (columnKey == null))
		{
			return null;
		}

		Map<C, V> row = backingMap.get(rowKey);
		if (null == row)
		{
			return null;
		}

		V value = row.remove(columnKey);
		if (row.isEmpty())
		{
			backingMap.remove(rowKey);
		}

		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<C, V> row(R rowKey)
	{
		if ((rowKey == null))
		{
			return null;
		}

		return backingMap.get(rowKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<R> rowKeySet()
	{
		return backingMap.keySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<R, Map<C, V>> rowMap()
	{
		return Collections.unmodifiableMap(backingMap);
	}

	/**
	 * 获取或创建Row
	 * 
	 * @param rowKey
	 * @return
	 */
	protected Map<C, V> getOrCreateRow(R rowKey)
	{
		Map<C, V> row = backingMap.get(rowKey);
		if (null == row)
		{
			row = createRow();
			backingMap.put(rowKey, row);
		}

		return row;
	}

	/**
	 * 创建Row
	 * 
	 * @return
	 */
	protected abstract Map<C, V> createRow();

}