package com.mizhousoft.commons.data.util;

import java.util.List;

import com.mizhousoft.commons.data.domain.Page;
import com.mizhousoft.commons.data.domain.PageImpl;
import com.mizhousoft.commons.data.domain.Pageable;

/**
 * 分页构建器
 *
 * @version
 */
public abstract class PageBuilder
{
	/**
	 * 构建分页
	 * 
	 * @param data
	 * @param pageable
	 * @param totalNumber
	 * @return
	 */
	public static <T> Page<T> build(List<T> data, Pageable pageable, long totalNumber)
	{
		Page<T> page = new PageImpl<T>(data, pageable, totalNumber);
		return page;
	}
}
