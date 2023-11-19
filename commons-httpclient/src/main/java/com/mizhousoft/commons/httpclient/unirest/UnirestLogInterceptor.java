package com.mizhousoft.commons.httpclient.unirest;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kong.unirest.core.Body;
import kong.unirest.core.BodyPart;
import kong.unirest.core.Config;
import kong.unirest.core.Header;
import kong.unirest.core.Headers;
import kong.unirest.core.HttpRequest;
import kong.unirest.core.HttpRequestSummary;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Interceptor;

/**
 * 日志拦截器
 *
 * @version
 */
public class UnirestLogInterceptor implements Interceptor
{
	private static Logger LOG = LoggerFactory.getLogger("http-outgoing");

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onRequest(HttpRequest<?> request, Config config)
	{
		if (!LOG.isDebugEnabled())
		{
			return;
		}

		LOG.debug(">> {} {}", request.getHttpMethod().toString(), request.getUrl());

		Headers headers = request.getHeaders();
		List<Header> headerList = headers.all();
		for (Header header : headerList)
		{
			LOG.debug(">> {}", header.toString());
		}

		Body body = request.getBody().orElse(null);
		if (null != body)
		{
			@SuppressWarnings("rawtypes")
			Collection<BodyPart> multiParts = body.multiParts();
			for (BodyPart<?> bodyPart : multiParts)
			{
				LOG.debug(">> {}", bodyPart.toString());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onResponse(HttpResponse<?> response, HttpRequestSummary request, Config config)
	{
		if (!LOG.isDebugEnabled())
		{
			return;
		}

		LOG.debug("<< {} {}", response.getStatus(), response.getStatusText());

		Headers headers = response.getHeaders();
		List<Header> headerList = headers.all();
		for (Header header : headerList)
		{
			LOG.debug("<< {}", header.toString());
		}

		if (null != response.getBody())
		{
			LOG.debug("<< {}", response.getBody().toString());
		}
	}
}
