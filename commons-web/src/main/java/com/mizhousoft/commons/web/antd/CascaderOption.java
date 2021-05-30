package com.mizhousoft.commons.web.antd;

import java.util.List;

/**
 * 级联选项
 *
 * @version
 */
public class CascaderOption
{
	// 值
	private String value;

	// 标题
	private String label;

	// 子列表
	private List<CascaderOption> children;

	/**
	 * 获取value
	 * 
	 * @return
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * 设置value
	 * 
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * 获取label
	 * 
	 * @return
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * 设置label
	 * 
	 * @param label
	 */
	public void setLabel(String label)
	{
		this.label = label;
	}

	/**
	 * 获取children
	 * 
	 * @return
	 */
	public List<CascaderOption> getChildren()
	{
		return children;
	}

	/**
	 * 设置children
	 * 
	 * @param children
	 */
	public void setChildren(List<CascaderOption> children)
	{
		this.children = children;
	}
}
