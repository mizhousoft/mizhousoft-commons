package com.mizhousoft.commons.web.i18n.constant;

/**
 * 语言
 * 
 * @version
 */
public enum Language
{
    // 英文
	EN("en_US"),
    // 中文
	ZH("zh_CN");

	private Language(String val)
	{
		this.value = val;
	}

	private String value;

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
	 * 获取语言
	 * 
	 * @param val
	 * @return
	 */
	public Language getLanguage(String val)
	{
		Language[] languages = Language.values();
		for (Language language : languages)
		{
			if (language.getValue().equalsIgnoreCase(val))
			{
				return language;
			}
		}

		throw new IllegalArgumentException(val + " is illegal language.");
	}
}
