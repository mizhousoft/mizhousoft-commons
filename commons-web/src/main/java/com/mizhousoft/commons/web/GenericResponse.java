package com.mizhousoft.commons.web;

/**
 * 泛型响应
 *
 * @version
 */
public class GenericResponse<T> extends ActionResponse
{
	private T data;

	/**
	 * 获取data
	 * 
	 * @return
	 */
	public T getData()
	{
		return data;
	}

	/**
	 * 设置data
	 * 
	 * @param data
	 */
	public void setData(T data)
	{
		this.data = data;
	}
}
