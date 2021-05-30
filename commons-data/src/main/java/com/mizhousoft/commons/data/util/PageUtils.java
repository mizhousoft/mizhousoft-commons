package com.mizhousoft.commons.data.util;

import com.mizhousoft.commons.data.domain.Pageable;

/**
 * 分页工具类
 *
 * @version
 */
public class PageUtils
{
	/**
	 * 计算行数偏移量
	 * 
	 * @param page
	 * @param total
	 * @return
	 */
	public static long calcRowOffset(Pageable pageRequest, long total)
	{
		if (0 == total)
		{
			return 0;
		}

		int pageSize = pageRequest.getPageSize();
		int pageNum = pageRequest.getPageNumber() - 1;
		long offset = pageSize * pageNum;
		if (offset < total)
		{
			return offset;
		}
		else
		{
			int num = (int) Math.ceil((double) total / (double) pageSize);
			if (num >= 1)
			{
				num = num - 1;
			}

			int rowOffset = num * pageSize;
			return rowOffset;
		}
	}
}
