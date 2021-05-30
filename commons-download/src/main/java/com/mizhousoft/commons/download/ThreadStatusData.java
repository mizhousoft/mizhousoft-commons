package com.mizhousoft.commons.download;

/**
 * 下载文件状态数据
 *
 * @version
 */
class ThreadStatusData
{
	// 下载开始偏移
	private final long start;

	// 下载结束的位置
	private final long end;

	// 已读字节数
	private long read;

	/**
	 * 构造函数
	 *
	 * @param start
	 * @param end
	 * @param read
	 */
	public ThreadStatusData(long start, long end, long read)
	{
		this.start = start;
		this.end = end;
		this.read = read;
	}

	/**
	 * 获取开始下载位置
	 * 
	 * @return
	 */
	public long getStartPosition()
	{
		long pos = start + read;
		return pos;
	}

	/**
	 * 增加已读字节数
	 * 
	 * @param len
	 */
	public void incrementRead(int len)
	{
		this.read = this.read + len;
	}

	/**
	 * 是否下载完成
	 * 
	 * @return
	 */
	public boolean isFinish()
	{
		long pos = start + read;
		if (pos >= end)
		{
			return true;
		}

		return false;
	}

	/**
	 * 获取start
	 * 
	 * @return
	 */
	public long getStart()
	{
		return start;
	}

	/**
	 * 获取end
	 * 
	 * @return
	 */
	public long getEnd()
	{
		return end;
	}

	/**
	 * 获取read
	 * 
	 * @return
	 */
	public long getRead()
	{
		return read;
	}
}
