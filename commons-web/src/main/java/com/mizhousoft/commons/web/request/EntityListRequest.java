package com.mizhousoft.commons.web.request;

import java.util.Set;

/**
 * 请求
 *
 * @version
 */
public class EntityListRequest<T>
{
	/**
	 * ID
	 */
	protected Set<T> ids;

	/**
	 * 获取ids
	 * 
	 * @return
	 */
	public Set<T> getIds()
	{
		return ids;
	}

	/**
	 * 设置ids
	 * 
	 * @param ids
	 */
	public void setIds(Set<T> ids)
	{
		this.ids = ids;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{\"");
		if (ids != null)
		{
			builder.append("ids\":\"");
			builder.append(ids);
		}
		builder.append("\"}");
		return builder.toString();
	}
}
