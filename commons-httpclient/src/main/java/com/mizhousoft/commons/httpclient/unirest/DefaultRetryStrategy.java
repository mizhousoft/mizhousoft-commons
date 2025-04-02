package com.mizhousoft.commons.httpclient.unirest;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.RetryStrategy;

/**
 * 重试策略
 *
 * @version
 */
public class DefaultRetryStrategy implements RetryStrategy
{
	/**
	 * 等待时间
	 */
	private long waitTime = 1000;

	/**
	 * 最大重试次数
	 */
	private int maxAttempts = 3;

	/**
	 * 构造函数
	 *
	 */
	public DefaultRetryStrategy()
	{
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param waitTime
	 * @param maxAttempts
	 */
	public DefaultRetryStrategy(long waitTime, int maxAttempts)
	{
		super();
		this.waitTime = waitTime;
		this.maxAttempts = maxAttempts;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRetryable(HttpResponse<?> response)
	{
		if (null == response)
		{
			return true;
		}
		else if (response.getStatus() != 200)
		{
			return true;
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getWaitTime(HttpResponse<?> response)
	{
		return waitTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxAttempts()
	{
		return maxAttempts;
	}
}
