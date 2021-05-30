package com.mizhousoft.commons.data.domain;

/**
 * 抽象界面分页信息
 * 
 * @version
 */
public interface Pageable
{
	/**
	 * 返回页数
	 * 
	 * @return
	 */
	int getPageNumber();

	/**
	 * 获取页大小
	 * 
	 * @return
	 */
	int getPageSize();

	/**
	 * 获取表记录偏移位ID
	 * 
	 * @return
	 */
	int getOffsetId();

	/**
	 * 根据页数和页大小计算相对于表总记录数的偏移位
	 * 
	 * @return
	 */
	long getOffset();

	/**
	 * 获取排序
	 * 
	 * @return
	 */
	Sort getSort();
}
