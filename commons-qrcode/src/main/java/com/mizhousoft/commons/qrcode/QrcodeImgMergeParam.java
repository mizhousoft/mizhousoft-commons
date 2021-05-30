package com.mizhousoft.commons.qrcode;

/**
 * 二维码和图片合并参数
 *
 * @version
 */
public class QrcodeImgMergeParam extends QrcodeParam
{
	// 图片路径
	private String imgPath;

	// 二维码左上角位置
	private int qrcodeLeft;

	// 二维码顶部位置
	private int qrcodeTop;

	/**
	 * 构造函数
	 *
	 * @param content
	 * @param imgPath
	 * @param qrcodeLeft
	 * @param qrcodeTop
	 */
	public QrcodeImgMergeParam(String content, String imgPath, int qrcodeLeft, int qrcodeTop)
	{
		super(content);
		this.imgPath = imgPath;
		this.qrcodeLeft = qrcodeLeft;
		this.qrcodeTop = qrcodeTop;
	}

	/**
	 * 获取imgPath
	 * 
	 * @return
	 */
	public String getImgPath()
	{
		return imgPath;
	}

	/**
	 * 设置imgPath
	 * 
	 * @param imgPath
	 */
	public void setImgPath(String imgPath)
	{
		this.imgPath = imgPath;
	}

	/**
	 * 获取qrcodeLeft
	 * 
	 * @return
	 */
	public int getQrcodeLeft()
	{
		return qrcodeLeft;
	}

	/**
	 * 设置qrcodeLeft
	 * 
	 * @param qrcodeLeft
	 */
	public void setQrcodeLeft(int qrcodeLeft)
	{
		this.qrcodeLeft = qrcodeLeft;
	}

	/**
	 * 获取qrcodeTop
	 * 
	 * @return
	 */
	public int getQrcodeTop()
	{
		return qrcodeTop;
	}

	/**
	 * 设置qrcodeTop
	 * 
	 * @param qrcodeTop
	 */
	public void setQrcodeTop(int qrcodeTop)
	{
		this.qrcodeTop = qrcodeTop;
	}
}
