package com.mizhousoft.commons.data.domain;

/**
 * 界面分页具体请求信息
 * 
 * @version
 */
public class PageRequest extends AbstractPageRequest
{
	private static final long serialVersionUID = 2353293716192396698L;

	// 排序
	private Sort sort;

	/**
	 * 获取排序
	 * 
	 * @return
	 */
	public Sort getSort()
	{
		return sort;
	}

	/**
	 * 设置sort
	 * 
	 * @param sort
	 */
	public void setSort(Sort sort)
	{
		this.sort = sort;
	}

	/**
	 * 判断是否相等
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (!(obj instanceof PageRequest))
		{
			return false;
		}

		PageRequest that = (PageRequest) obj;
		return super.equals(that) && this.sort.equals(that.sort);
	}

	/**
	 * HashCode
	 * 
	 * @return
	 */
	@Override
	public int hashCode()
	{
		int result = 17;

		result = super.hashCode();
		result = 31 * result + (null == sort ? 0 : sort.hashCode());

		return result;
	}
}
