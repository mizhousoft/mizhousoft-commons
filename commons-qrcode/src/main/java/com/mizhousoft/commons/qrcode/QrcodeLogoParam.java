package com.mizhousoft.commons.qrcode;

/**
 * 二维码参数
 *
 * @version
 */
public class QrcodeLogoParam extends QrcodeParam
{
	// LOGO路径
	private String logoPath;

	/**
	 * 构造函数
	 *
	 * @param content
	 */
	public QrcodeLogoParam(String content, String logoPath)
	{
		super(content);

		this.logoPath = logoPath;
	}

	/**
	 * 获取logoPath
	 * 
	 * @return
	 */
	public String getLogoPath()
	{
		return logoPath;
	}

	/**
	 * 设置logoPath
	 * 
	 * @param logoPath
	 */
	public void setLogoPath(String logoPath)
	{
		this.logoPath = logoPath;
	}
}
