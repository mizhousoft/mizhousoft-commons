package com.mizhousoft.commons.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 固定线程池
 *
 * @version
 */
public class FixedThreadPool
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
		return new ThreadPoolExecutor(nThreads, nThreads, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
		        new DefaultThreadFactory(name));
	}
}
