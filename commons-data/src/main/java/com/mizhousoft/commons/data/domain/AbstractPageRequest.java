package com.mizhousoft.commons.data.domain;

import java.io.Serializable;

import com.mizhousoft.commons.data.constant.PaginationConstants;

/**
 * 抽象分页类
 *
 * @version
 */
public abstract class AbstractPageRequest implements Pageable, Serializable
{
	private static final long serialVersionUID = 4171958473795137087L;

	// 页数
	private int pageNumber = PaginationConstants.DEFAULT_PAGE_NUMBER;

	// 页大小
	private int pageSize = PaginationConstants.DEFAULT_PAGE_SIZE;

	// 表记录偏移位ID
	private int offsetId = 0;

	/**
	 * 获取pageNumber
	 * 
	 * @return
	 */
	public int getPageNumber()
	{
		return pageNumber;
	}

	/**
	 * 设置pageNumber
	 * 
	 * @param pageNumber
	 */
	public void setPageNumber(int pageNumber)
	{
		if (pageNumber <= 0)
		{
			pageNumber = PaginationConstants.DEFAULT_PAGE_NUMBER;
		}

		this.pageNumber = pageNumber;
	}

	/**
	 * 获取pageSize
	 * 
	 * @return
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * 获取表记录偏移位ID
	 * 
	 * @return
	 */
	public int getOffsetId()
	{
		return offsetId;
	}

	/**
	 * 设置offsetId
	 * 
	 * @param offsetId
	 */
	public void setOffsetId(int offsetId)
	{
		this.offsetId = offsetId;
	}

	/**
	 * 设置pageSize
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize)
	{
		if (pageSize <= 0)
		{
			pageSize = PaginationConstants.DEFAULT_PAGE_SIZE;
		}
		
		this.pageSize = pageSize;
	}

	/**
	 * 根据页数和页大小计算相对于表总记录数的偏移位
	 * 
	 * @return
	 */
	public long getOffset()
	{
		return pageNumber * pageSize;
	}

	@Override
	public int hashCode()
	{

		final int prime = 31;
		int result = 1;

		result = prime * result + pageNumber;
		result = prime * result + pageSize;

		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj == null || getClass() != obj.getClass())
		{
			return false;
		}

		AbstractPageRequest other = (AbstractPageRequest) obj;
		return this.pageNumber == other.pageNumber && this.pageSize == other.pageSize;
	}
}
