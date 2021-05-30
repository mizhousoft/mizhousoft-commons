package com.mizhousoft.commons.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 缓存线程池
 *
 * @version
 */
public class CachedThreadPool
{
	/**
	 * 创建一个线程池
	 * 
	 * @param nThreads
	 * @param name
	 * @return
	 */
	public static ExecutorService newThreadPool(int nThreads, String name)
	{
		return new ThreadPoolExecutor(nThreads, 100, 1, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>(),
		        new DefaultThreadFactory(name));
	}

	/**
	 * 创建调度线程池
	 * 
	 * @param nThreads
	 * @param name
	 * @return
	 */
	public static ScheduledExecutorService newScheduledThreadPool(int nThreads, String name)
	{
		return new ScheduledThreadPoolExecutor(nThreads, new DefaultThreadFactory(name));
	}
}
