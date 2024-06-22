package com.mizhousoft.commons.web.request;

/**
 * 请求
 *
 * @version
 */
public class EntityRequest<T>
{
	/**
	 * ID
	 */
	protected T id;

	/**
	 * 获取id
	 * 
	 * @return
	 */
	public T getId()
	{
		return id;
	}

	/**
	 * 设置id
	 * 
	 * @param id
	 */
	public void setId(T id)
	{
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{\"");
		if (id != null)
		{
			builder.append("id\":\"");
			builder.append(id);
		}
		builder.append("\"}");
		return builder.toString();
	}
}
