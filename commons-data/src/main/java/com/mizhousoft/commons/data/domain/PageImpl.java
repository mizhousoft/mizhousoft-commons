package com.mizhousoft.commons.data.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 页
 * 
 */
public class PageImpl<T> implements Page<T>, Serializable
{
	private static final long serialVersionUID = 2889852808653462090L;

	// 页数据
	private List<T> content = new ArrayList<T>();

	// 界面分页信息
	private Pageable pageable;

	// 总记录数
	private long totalNumber;

	/**
	 * 构造函数
	 *
	 */
	public PageImpl()
	{

	}

	/**
	 * 构造函数
	 * 
	 * @param content
	 * @param pageable
	 * @param totalNumber
	 */
	public PageImpl(List<T> content, Pageable pageable, long totalNumber)
	{
		if (null != content)
		{
			this.content.addAll(content);
		}

		this.totalNumber = totalNumber;
		this.pageable = pageable;
	}

	/**
	 * 获取当前页数，总是正数并小于或等于页总数
	 * 
	 * @return
	 */
	public int getPageNumber()
	{
		int pageNumber = 0;
		if (null != pageable)
		{
			pageNumber = pageable.getPageNumber();
			if (pageNumber > getTotalPage())
			{
				pageNumber = getTotalPage();
			}
		}

		if (0 == pageNumber)
		{
			pageNumber = 1;
		}

		return pageNumber;
	}

	/**
	 * 获取页大小
	 * 
	 * @return
	 */
	public int getPageSize()
	{
		return pageable == null ? 0 : pageable.getPageSize();
	}

	/**
	 * 获取页总数
	 * 
	 * @return
	 */
	public int getTotalPage()
	{
		return getPageSize() == 0 ? 0 : (int) Math.ceil((double) totalNumber / (double) getPageSize());
	}

	/**
	 * 获取总记录数量
	 * 
	 * @return
	 */
	public long getTotalNumber()
	{
		return totalNumber;
	}

	/**
	 * 设置总记录数量
	 * 
	 * @param totalNumber
	 */
	public void setTotalNumber(long totalNumber)
	{
		this.totalNumber = totalNumber;
	}

	/**
	 * 设置pageable
	 * 
	 * @param pageable
	 */
	public void setPageable(Pageable pageable)
	{
		this.pageable = pageable;
	}

	/**
	 * 是否有前一页
	 * 
	 * @return
	 */
	public boolean hasPreviousPage()
	{
		return getPageNumber() > 1;
	}

	/**
	 * 当前页是否第一页
	 * 
	 * @return
	 */
	public boolean isFirstPage()
	{
		return !hasPreviousPage();
	}

	/**
	 * 是否有下一页
	 * 
	 * @return
	 */
	public boolean hasNextPage()
	{
		return (getPageNumber() * getPageSize()) < totalNumber;
	}

	/**
	 * 当前页是否最后一页
	 * 
	 * @return
	 */
	public boolean isLastPage()
	{
		return !hasNextPage();
	}

	/**
	 * 获取页数据的Iterator
	 * 
	 * @return
	 */
	public Iterator<T> iterator()
	{
		return content.iterator();
	}

	/**
	 * 获取页数据
	 * 
	 * @return
	 */
	public List<T> getContent()
	{
		return Collections.unmodifiableList(content);
	}

	/**
	 * 设置content
	 * 
	 * @param content
	 */
	public void setContent(List<T> content)
	{
		if (null != content)
		{
			this.content.addAll(content);
		}
	}

	/**
	 * 页是否有数据
	 * 
	 * @return
	 */
	public boolean hasContent()
	{
		return !content.isEmpty();
	}

	/**
	 * 获取页的排序
	 * 
	 * @return
	 */
	public Sort getSort()
	{
		return pageable == null ? null : pageable.getSort();
	}

	@Override
	public String toString()
	{
		String contentType = "UNKNOWN";

		if (content.size() > 0)
		{
			contentType = content.get(0).getClass().getName();
		}

		return String.format("Page %s of %d containing %s instances", getPageNumber(), getTotalPage(), contentType);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (!(obj instanceof PageImpl<?>))
		{
			return false;
		}

		PageImpl<?> that = (PageImpl<?>) obj;

		boolean totalEqual = this.totalNumber == that.totalNumber;
		boolean contentEqual = this.content.equals(that.content);
		boolean pageableEqual = this.pageable == null ? that.pageable == null : this.pageable.equals(that.pageable);

		return totalEqual && contentEqual && pageableEqual;
	}

	@Override
	public int hashCode()
	{
		int result = 17;

		result = 31 * result + (int) (totalNumber ^ totalNumber >>> 32);
		result = 31 * result + (pageable == null ? 0 : pageable.hashCode());
		result = 31 * result + content.hashCode();

		return result;
	}
}
