package com.mizhousoft.commons.data.domain;

import java.util.Iterator;
import java.util.List;

/**
 * 页接口
 * 页是子表的对象列表，它允许在包含整个列表中的位置。
 * 
 * @version @param <T>
 */
public interface Page<T> extends Iterable<T>
{
	/**
	 * 获取当前页数，总是正数并小于或等于页总数
	 * 
	 * @return
	 */
	int getPageNumber();

	/**
	 * 获取页大小
	 * 
	 * @return
	 */
	int getPageSize();

	/**
	 * 获取页总数
	 * 
	 * @return
	 */
	int getTotalPage();

	/**
	 * 获取总记录数量
	 * 
	 * @return
	 */
	long getTotalNumber();

	/**
	 * 是否有前一页
	 * 
	 * @return
	 */
	boolean hasPreviousPage();

	/**
	 * 是否有下一页
	 * 
	 * @return
	 */
	boolean hasNextPage();

	/**
	 * 当前页是否第一页
	 * 
	 * @return
	 */
	boolean isFirstPage();

	/**
	 * 当前页是否最后一页
	 * 
	 * @return
	 */
	boolean isLastPage();

	/**
	 * 获取页数据的Iterator
	 * 
	 * @return
	 */
	Iterator<T> iterator();

	/**
	 * 获取页数据
	 * 
	 * @return
	 */
	List<T> getContent();

	/**
	 * 页是否有数据
	 * 
	 * @return
	 */
	boolean hasContent();

	/**
	 * 获取页的排序
	 * 
	 * @return
	 */
	Sort getSort();
}