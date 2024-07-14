package com.mizhousoft.commons.web.jstree;

import java.util.List;

/**
 * 树节点
 *
 * @version
 */
public class JSTreeNode
{
	// ID
	private String id;

	// 数据ID
	private String dataId;

	// 父节点
	private String parent;

	// 文本
	private String text;

	// 图标
	private String icon;

	// 子节点
	private List<JSTreeNode> children;

	// 节点状态
	private JSTreeNodeState state = new JSTreeNodeState();

	// 扩展数据
	private String extra;

	/**
	 * 获取id
	 * 
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * 设置id
	 * 
	 * @param id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * 获取dataId
	 * 
	 * @return
	 */
	public String getDataId()
	{
		return dataId;
	}

	/**
	 * 设置dataId
	 * 
	 * @param dataId
	 */
	public void setDataId(String dataId)
	{
		this.dataId = dataId;
	}

	/**
	 * 获取parent
	 * 
	 * @return
	 */
	public String getParent()
	{
		return parent;
	}

	/**
	 * 设置parent
	 * 
	 * @param parent
	 */
	public void setParent(String parent)
	{
		this.parent = parent;
	}

	/**
	 * 获取text
	 * 
	 * @return
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * 设置text
	 * 
	 * @param text
	 */
	public void setText(String text)
	{
		this.text = text;
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
	 * 获取children
	 * 
	 * @return
	 */
	public List<JSTreeNode> getChildren()
	{
		return children;
	}

	/**
	 * 设置children
	 * 
	 * @param children
	 */
	public void setChildren(List<JSTreeNode> children)
	{
		this.children = children;
	}

	/**
	 * 获取state
	 * 
	 * @return
	 */
	public JSTreeNodeState getState()
	{
		return state;
	}

	/**
	 * 设置state
	 * 
	 * @param state
	 */
	public void setState(JSTreeNodeState state)
	{
		this.state = state;
	}

	/**
	 * 获取extra
	 * 
	 * @return
	 */
	public String getExtra()
	{
		return extra;
	}

	/**
	 * 设置extra
	 * 
	 * @param extra
	 */
	public void setExtra(String extra)
	{
		this.extra = extra;
	}
}
