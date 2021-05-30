package com.mizhousoft.commons.data.util;

import com.mizhousoft.commons.data.constant.PaginationConstants;
import com.mizhousoft.commons.data.domain.PageRequest;
import com.mizhousoft.commons.data.domain.Pageable;

/**
 * PageRequest构建器
 *
 * @version
 */
public abstract class PageRequestBuilder
{
	/**
	 * 构建分页请求
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public static Pageable build(Integer pageNumber, Integer pageSize)
	{
		if (null == pageNumber || pageNumber.intValue() < 1)
		{
			pageNumber = PaginationConstants.DEFAULT_PAGE_NUMBER;
		}

		if (null == pageSize || pageSize.intValue() < 1)
		{
			pageSize = PaginationConstants.DEFAULT_PAGE_SIZE;
		}

		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);

		return pageRequest;
	}

	/**
	 * 构建默认分页
	 * 
	 * @return
	 */
	public static Pageable buildDefault()
	{
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageNumber(PaginationConstants.DEFAULT_PAGE_NUMBER);
		pageRequest.setPageSize(PaginationConstants.DEFAULT_PAGE_SIZE);

		return pageRequest;
	}
}
