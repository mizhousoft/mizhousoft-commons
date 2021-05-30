package com.mizhousoft.commons.web.i18n.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;

/**
 * Message ResourceBundle
 * 
 * @version
 */
public class MessageResourceBundle extends PropertyResourceBundle
{
	/**
	 * 语言
	 */
	protected String localeString;

	/**
	 * Bundle Name
	 */
	protected String bundleName;

	/**
	 * 构造函数
	 * 
	 * @param stream
	 * @throws IOException
	 */
	public MessageResourceBundle(InputStream stream) throws IOException
	{
		super(stream);
	}

	/**
	 * 获取localeString
	 * 
	 * @return
	 */
	public String getLocaleString()
	{
		return localeString;
	}

	/**
	 * 设置localeString
	 * 
	 * @param localeString
	 */
	public void setLocaleString(String localeString)
	{
		this.localeString = localeString;
	}

	/**
	 * 获取bundleName
	 * 
	 * @return
	 */
	public String getBundleName()
	{
		return bundleName;
	}

	/**
	 * 设置bundleName
	 * 
	 * @param bundleName
	 */
	public void setBundleName(String bundleName)
	{
		this.bundleName = bundleName;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bundleName == null) ? 0 : bundleName.hashCode());
		result = prime * result + ((localeString == null) ? 0 : localeString.hashCode());
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj == null)
		{
			return false;
		}

		if (getClass() != obj.getClass())
		{
			return false;
		}
		MessageResourceBundle other = (MessageResourceBundle) obj;
		if (bundleName == null)
		{
			if (other.bundleName != null)
			{
				return false;
			}
		}
		else if (!bundleName.equals(other.bundleName))
		{
			return false;
		}
		if (localeString == null)
		{
			if (other.localeString != null)
			{
				return false;
			}
		}
		else if (!localeString.equals(other.localeString))
		{
			return false;
		}

		return true;
	}
}
