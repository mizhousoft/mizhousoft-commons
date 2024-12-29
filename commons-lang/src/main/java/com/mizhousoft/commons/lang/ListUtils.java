package com.mizhousoft.commons.lang;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 列表工具类
 *
 * @version
 */
public abstract class ListUtils
{
	/**
	 * 如果为null，返回空列表
	 * 
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> List<T> emptyIfNull(final List<T> list)
	{
		return list == null ? Collections.<T> emptyList() : list;
	}

	/**
	 * 如果为null，返回默认列表
	 * 
	 * @param <T>
	 * @param list
	 * @param defaultList
	 * @return
	 */
	public static <T> List<T> defaultIfNull(final List<T> list, final List<T> defaultList)
	{
		return list == null ? defaultList : list;
	}

	/**
	 * 列表分片
	 * 
	 * @param <T>
	 * @param list
	 * @param size
	 * @return
	 */
	public static <T> List<List<T>> partition(final List<T> list, final int size)
	{
		if (list == null)
		{
			throw new NullPointerException("List must not be null");
		}
		if (size <= 0)
		{
			throw new IllegalArgumentException("Size must be greater than 0");
		}

		return new Partition<>(list, size);
	}

	/**
	 * 合并集合
	 * 
	 * @param <T>
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> List<T> merge(final List<? extends T> a, final List<? extends T> b)
	{
		List<T> list = new ArrayList<>(a.size() + b.size());

		list.addAll(a);
		list.addAll(b);

		return list;
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
	public static <T> List<T> merge(final List<? extends T> a, final List<? extends T> b, final List<? extends T> c)
	{
		List<T> list = new ArrayList<>(a.size() + b.size() + c.size());

		list.addAll(a);
		list.addAll(b);
		list.addAll(c);

		return list;
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
	public static <T> List<T> merge(final List<? extends T> a, final List<? extends T> b, final List<? extends T> c,
	        final List<? extends T> d)
	{
		List<T> list = new ArrayList<>(a.size() + b.size() + c.size() + d.size());

		list.addAll(a);
		list.addAll(b);
		list.addAll(c);
		list.addAll(d);

		return list;
	}

	/**
	 * 分片，代码来自于 Apache Collections
	 *
	 * @version ListUtils@param <T>
	 */
	private static class Partition<T> extends AbstractList<List<T>>
	{
		private final List<T> list;

		private final int size;

		private Partition(final List<T> list, final int size)
		{
			this.list = list;
			this.size = size;
		}

		@Override
		public List<T> get(final int index)
		{
			final int listSize = size();
			if (index < 0)
			{
				throw new IndexOutOfBoundsException("Index " + index + " must not be negative");
			}
			if (index >= listSize)
			{
				throw new IndexOutOfBoundsException("Index " + index + " must be less than size " + listSize);
			}
			final int start = index * size;
			final int end = Math.min(start + size, list.size());
			return list.subList(start, end);
		}

		@Override
		public int size()
		{
			return (int) Math.ceil((double) list.size() / (double) size);
		}

		@Override
		public boolean isEmpty()
		{
			return list.isEmpty();
		}
	}
}
