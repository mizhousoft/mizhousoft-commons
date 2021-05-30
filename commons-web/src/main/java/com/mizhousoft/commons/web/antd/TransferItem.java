package com.mizhousoft.commons.web.antd;

/**
 * 穿梭项
 *
 * @version
 */
public class TransferItem
{
	// Key
	private String key;

	// 标题
	private String title;

	// 描述
	private String description;

	// 是否禁用
	private boolean disabled;

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
	 * 获取description
	 * 
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * 设置description
	 * 
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
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
}
