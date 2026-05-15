package com.mizhousoft.commons.web.antd;

import java.util.List;

/**
 * 步骤
 *
 */
public class Steps
{
	/**
	 * 第几步
	 */
	private int current;

	/**
	 * 步骤项
	 */
	private List<StepItem> items;

	/**
	 * 步骤项
	 *
	 */
	public static class StepItem
	{
		/**
		 * 标题
		 */
		private String title;

		/**
		 * 子标题
		 */
		private String subTitle;

		/**
		 * 步骤的详情描述
		 */
		private String content;

		/**
		 * 状态
		 */
		private String status;

		/**
		 * 是否禁用
		 */
		private Boolean disabled;

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
		 * 获取subTitle
		 * 
		 * @return
		 */
		public String getSubTitle()
		{
			return subTitle;
		}

		/**
		 * 设置subTitle
		 * 
		 * @param subTitle
		 */
		public void setSubTitle(String subTitle)
		{
			this.subTitle = subTitle;
		}

		/**
		 * 获取content
		 * 
		 * @return
		 */
		public String getContent()
		{
			return content;
		}

		/**
		 * 设置content
		 * 
		 * @param content
		 */
		public void setContent(String content)
		{
			this.content = content;
		}

		/**
		 * 获取status
		 * 
		 * @return
		 */
		public String getStatus()
		{
			return status;
		}

		/**
		 * 设置status
		 * 
		 * @param status
		 */
		public void setStatus(String status)
		{
			this.status = status;
		}

		/**
		 * 获取disabled
		 * 
		 * @return
		 */
		public Boolean getDisabled()
		{
			return disabled;
		}

		/**
		 * 设置disabled
		 * 
		 * @param disabled
		 */
		public void setDisabled(Boolean disabled)
		{
			this.disabled = disabled;
		}
	}

	/**
	 * 获取current
	 * 
	 * @return
	 */
	public int getCurrent()
	{
		return current;
	}

	/**
	 * 设置current
	 * 
	 * @param current
	 */
	public void setCurrent(int current)
	{
		this.current = current;
	}

	/**
	 * 获取items
	 * 
	 * @return
	 */
	public List<StepItem> getItems()
	{
		return items;
	}

	/**
	 * 设置items
	 * 
	 * @param items
	 */
	public void setItems(List<StepItem> items)
	{
		this.items = items;
	}
}
