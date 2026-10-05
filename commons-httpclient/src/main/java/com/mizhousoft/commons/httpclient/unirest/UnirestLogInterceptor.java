package com.mizhousoft.commons.httpclient.unirest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kong.unirest.core.Body;
import kong.unirest.core.BodyPart;
import kong.unirest.core.Config;
import kong.unirest.core.Header;
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

		List<String> lines = buildProtoLines(request);
		for (String line : lines)
		{
			LOG.debug(line);
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

		List<String> lines = buildProtoLines(response);
		for (String line : lines)
		{
			LOG.debug(line);
		}
	}

	/**
	 * 构建协议行
	 * 
	 * @param request
	 * @return
	 */
	public static List<String> buildProtoLines(HttpRequest<?> request)
	{
		List<String> lines = new ArrayList<>(10);

		// 请求行
		lines.add(String.format(">> %s %s", request.getHttpMethod(), request.getUrl()));

		// 请求头
		for (Header header : request.getHeaders().all())
		{
			lines.add(">> " + header);
		}

		// 请求体
		Body body = request.getBody().orElse(null);
		if (body != null)
		{
			for (BodyPart<?> bodyPart : body.multiParts())
			{
				lines.add(">> " + bodyPart);
			}
		}

		return lines;
	}

	/**
	 * 构建协议行
	 * 
	 * @param response
	 * @return
	 */
	public static List<String> buildProtoLines(HttpResponse<?> response)
	{
		List<String> lines = new ArrayList<>(10);

		// 状态行
		lines.add(String.format("<< %s %s", response.getStatus(), response.getStatusText()));

		// 响应头
		for (Header header : response.getHeaders().all())
		{
			lines.add("<< " + header);
		}

		// 响应体（空行分隔）
		if (response.getBody() != null)
		{
			lines.add("<< ");
			lines.add("<< " + response.getBody());
		}

		return lines;
	}
}
