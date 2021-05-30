package com.mizhousoft.commons.web.antd;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 树节点
 *
 * @version
 */
public class TreeNode
{
	// 树节点显示的内容
	private String title;

	// key
	private String key;

	// value
	private String value;

	// 是否禁用
	private boolean disabled;

	// 是否叶子节点
	@JsonProperty("isLeaf")
	private boolean leaf;

	// 设置节点是否可被选中
	private boolean selectable = true;

	// 图标
	private String icon;

	// 树节点ID
	private String nodeId;

	// 组件名称
	private String componentName;

	// 子节点
	private List<TreeNode> children;

	/**
	 * 获取title
	 * 
	 * @return
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 设置title
	 * 
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 获取key
	 * 
	 * @return
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * 设置key
	 * 
	 * @param key
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

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
	 * 获取disabled
	 * 
	 * @return
	 */
	public boolean isDisabled()
	{
		return disabled;
	}

	/**
	 * 设置disabled
	 * 
	 * @param disabled
	 */
	public void setDisabled(boolean disabled)
	{
		this.disabled = disabled;
	}

	/**
	 * 获取leaf
	 * 
	 * @return
	 */
	public boolean isLeaf()
	{
		return leaf;
	}

	/**
	 * 设置leaf
	 * 
	 * @param leaf
	 */
	public void setLeaf(boolean leaf)
	{
		this.leaf = leaf;
	}

	/**
	 * 获取selectable
	 * 
	 * @return
	 */
	public boolean isSelectable()
	{
		return selectable;
	}

	/**
	 * 设置selectable
	 * 
	 * @param selectable
	 */
	public void setSelectable(boolean selectable)
	{
		this.selectable = selectable;
	}

	/**
	 * 获取icon
	 * 
	 * @return
	 */
	public String getIcon()
	{
		return icon;
	}

	/**
	 * 设置icon
	 * 
	 * @param icon
	 */
	public void setIcon(String icon)
	{
		this.icon = icon;
	}

	/**
	 * 获取nodeId
	 * 
	 * @return
	 */
	public String getNodeId()
	{
		return nodeId;
	}

	/**
	 * 设置nodeId
	 * 
	 * @param nodeId
	 */
	public void setNodeId(String nodeId)
	{
		this.nodeId = nodeId;
	}

	/**
	 * 获取componentName
	 * 
	 * @return
	 */
	public String getComponentName()
	{
		return componentName;
	}

	/**
	 * 设置componentName
	 * 
	 * @param componentName
	 */
	public void setComponentName(String componentName)
	{
		this.componentName = componentName;
	}

	/**
	 * 获取children
	 * 
	 * @return
	 */
	public List<TreeNode> getChildren()
	{
		return children;
	}

	/**
	 * 设置children
	 * 
	 * @param children
	 */
	public void setChildren(List<TreeNode> children)
	{
		this.children = children;
	}
}
