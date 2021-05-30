package com.mizhousoft.commons.qrcode;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码参数
 *
 * @version
 */
public class QrcodeParam
{
	// 二维码宽度
	protected int width = 300;

	// 二维码高度
	protected int height = 300;

	// 前景色
	protected int onColor = 0xFF000000;

	// 背景色
	protected int offColor = 0xFFFFFFFF;

	// 容错率
	protected ErrorCorrectionLevel level = ErrorCorrectionLevel.M;

	// 白边大小，取值范围0~4
	protected int margin = 0;

	// 二维码内容
	protected String content;

	/**
	 * 构造函数
	 *
	 * @param content
	 */
	public QrcodeParam(String content)
	{
		super();
		this.content = content;
	}

	/**
	 * 获取width
	 * 
	 * @return
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * 设置width
	 * 
	 * @param width
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * 获取height
	 * 
	 * @return
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * 设置height
	 * 
	 * @param height
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	 * 获取onColor
	 * 
	 * @return
	 */
	public int getOnColor()
	{
		return onColor;
	}

	/**
	 * 设置onColor
	 * 
	 * @param onColor
	 */
	public void setOnColor(int onColor)
	{
		this.onColor = onColor;
	}

	/**
	 * 获取offColor
	 * 
	 * @return
	 */
	public int getOffColor()
	{
		return offColor;
	}

	/**
	 * 设置offColor
	 * 
	 * @param offColor
	 */
	public void setOffColor(int offColor)
	{
		this.offColor = offColor;
	}

	/**
	 * 获取level
	 * 
	 * @return
	 */
	public ErrorCorrectionLevel getLevel()
	{
		return level;
	}

	/**
	 * 设置level
	 * 
	 * @param level
	 */
	public void setLevel(ErrorCorrectionLevel level)
	{
		this.level = level;
	}

	/**
	 * 获取margin
	 * 
	 * @return
	 */
	public int getMargin()
	{
		return margin;
	}

	/**
	 * 设置margin
	 * 
	 * @param margin
	 */
	public void setMargin(int margin)
	{
		this.margin = margin;
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
}
