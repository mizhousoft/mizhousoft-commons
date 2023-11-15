package com.mizhousoft.commons.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂类
 *
 * @version
 */
public class DefaultThreadFactory implements ThreadFactory
{
	// 计算器
	private final AtomicInteger mThreadNum = new AtomicInteger(1);

	// 线程名前缀
	private final String mPrefix;

	// 线程组
	private final ThreadGroup mGroup;

	/**
	 * 构造函数
	 *
	 * @param prefix
	 */
	public DefaultThreadFactory(String prefix)
	{
		mPrefix = prefix + "-thread-";
		mGroup = Thread.currentThread().getThreadGroup();
	}

	/**
	 * 创建一个新线程
	 * 
	 * @param runnable
	 * @return
	 */
	public Thread newThread(Runnable runnable)
	{
		String name = mPrefix + mThreadNum.getAndIncrement();
		Thread ret = new Thread(mGroup, runnable, name, 0);
		return ret;
	}
}