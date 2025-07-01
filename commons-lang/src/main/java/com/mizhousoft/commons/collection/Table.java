package com.mizhousoft.commons.collection;

import java.util.Map;
import java.util.Set;

/**
 * Table
 * 参考guava
 *
 */
public interface Table<R extends Object, C extends Object, V extends Object>
{
	/**
	 * 是否包含
	 * 
	 * @param rowKey
	 * @param columnKey
	 * @return
	 */
	boolean contains(Object rowKey, Object columnKey);

	/**
	 * 是否包含
	 * 
	 * @param rowKey
	 * @return
	 */
	boolean containsRow(Object rowKey);

	/**
	 * 获取值
	 * 
	 * @param rowKey
	 * @param columnKey
	 * @return
	 */
	V get(Object rowKey, Object columnKey);

	/**
	 * 是否为空
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 获取大小
	 * 
	 * @return
	 */
	int size();

	/**
	 * 清空
	 */
	void clear();

	/**
	 * 设置值
	 * 
	 * @param rowKey
	 * @param columnKey
	 * @param value
	 * @return
	 */
	V put(R rowKey, C columnKey, V value);

	/**
	 * 设置值
	 * 
	 * @param table
	 */
	void putAll(Table<R, C, V> table);

	/**
	 * 移除值
	 * 
	 * @param rowKey
	 * @return
	 */
	Map<C, V> remove(Object rowKey);

	/**
	 * 移除值
	 * 
	 * @param rowKey
	 * @param columnKey
	 * @return
	 */
	V remove(Object rowKey, Object columnKey);

	/**
	 * 获取行值
	 * 
	 * @param key
	 * @return
	 */
	Map<C, V> row(R key);

	/**
	 * 获取key
	 * 
	 * @return
	 */
	Set<R> rowKeySet();

	/**
	 * 获取值
	 * 
	 * @return
	 */
	Map<R, Map<C, V>> rowMap();

}