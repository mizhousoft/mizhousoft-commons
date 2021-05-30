package com.mizhousoft.commons.web.jstree;

/**
 * 节点状态
 *
 * @version
 */
public class JSTreeNodeState
{
	// 是否打开
	private boolean opened;

	// 是否禁用
	private boolean disabled;

	// 是否选择
	private boolean selected;

	/**
	 * 获取opened
	 * 
	 * @return
	 */
	public boolean isOpened()
	{
		return opened;
	}

	/**
	 * 设置opened
	 * 
	 * @param opened
	 */
	public void setOpened(boolean opened)
	{
		this.opened = opened;
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
	 * 获取selected
	 * 
	 * @return
	 */
	public boolean isSelected()
	{
		return selected;
	}

	/**
	 * 设置selected
	 * 
	 * @param selected
	 */
	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
}
