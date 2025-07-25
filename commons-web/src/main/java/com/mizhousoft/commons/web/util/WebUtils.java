package com.mizhousoft.commons.web.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.commons.lang.CharEncoding;
import com.mizhousoft.commons.lang.CollectionUtils;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * WebUtils
 *
 * @version
 */
public abstract class WebUtils
{
	private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

	private static final String INCLUDE_CONTEXT_PATH_ATTRIBUTE = "javax.servlet.include.context_path";

	private static final String INCLUDE_REQUEST_URI_ATTRIBUTE = "javax.servlet.include.request_uri";

	private static final String DEFAULT_CHARACTER_ENCODING = "ISO-8859-1";

	/**
	 * 获取HttpServletRequest
	 * 
	 * @param request
	 * @return
	 */
	public static HttpServletRequest getHttpRequest(ServletRequest request)
	{
		if (request instanceof HttpServletRequest)
		{
			return (HttpServletRequest) request;
		}

		throw new IllegalArgumentException("ServletRequest is not HttpServletRequest.");
	}

	/**
	 * 获取HttpServletResponse
	 * 
	 * @param response
	 * @return
	 */
	public static HttpServletResponse getHttpResponse(ServletResponse response)
	{
		if (response instanceof HttpServletResponse)
		{
			return (HttpServletResponse) response;
		}

		throw new IllegalArgumentException("ServletResponse is not HttpServletResponse.");
	}

	/**
	 * 获取请求body
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestBody(ServletRequest request) throws IOException
	{
		try (InputStream istream = request.getInputStream(); ByteArrayOutputStream ostream = new ByteArrayOutputStream();)
		{

			byte[] bytes = new byte[1024];
			int size = istream.read(bytes);
			while (size > 0)
			{
				ostream.write(bytes, 0, size);
				size = istream.read(bytes);
			}

			String body = ostream.toString(CharEncoding.UTF8_NAME);
			return body;
		}
	}

	/**
	 * 获取客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIPAddress(ServletRequest request)
	{
		HttpServletRequest httpRequest = getHttpRequest(request);

		String ip = httpRequest.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = httpRequest.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = httpRequest.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = httpRequest.getRemoteAddr();
		}

		ip = StringUtils.trimToNull(ip);

		return ip;
	}

	public static List<String> getRemoteIPAddressList(ServletRequest request)
	{
		String ips = getRemoteIPAddress(request);
		if (null == ips)
		{
			return Collections.emptyList();
		}

		String[] values = StringUtils.split(ips, ",");

		List<String> list = new ArrayList<>(values.length);
		for (String value : values)
		{
			list.add(value.trim());
		}

		return list;
	}

	public static String getFirstRemoteIPAddress(ServletRequest request)
	{
		List<String> ips = getRemoteIPAddressList(request);
		if (CollectionUtils.isEmpty(ips))
		{
			return null;
		}
		else
		{
			return ips.iterator().next();
		}
	}

	public static String getContextPath(HttpServletRequest request)
	{
		String contextPath = (String) request.getAttribute(INCLUDE_CONTEXT_PATH_ATTRIBUTE);
		if (contextPath == null)
		{
			contextPath = request.getContextPath();
		}
		contextPath = normalize(decodeRequestString(request, contextPath));
		if ("/".equals(contextPath))
		{
			// the normalize method will return a "/" and includes on Jetty, will also be a "/".
			contextPath = "";
		}

		return contextPath;
	}

	public static String getRequestUri(HttpServletRequest request)
	{
		String uri = (String) request.getAttribute(INCLUDE_REQUEST_URI_ATTRIBUTE);
		if (uri == null)
		{
			uri = request.getRequestURI();
		}
		return normalize(decodeAndCleanUriString(request, uri));
	}

	public static String getPathWithinApplication(HttpServletRequest request)
	{
		String contextPath = getContextPath(request);
		String requestUri = getRequestUri(request);
		if (Strings.CI.startsWith(requestUri, contextPath))
		{
			// Normal case: URI contains context path.
			String path = requestUri.substring(contextPath.length());
			return (StringUtils.isNotBlank(path) ? path : "/");
		}
		else
		{
			// Special case: rather unusual.
			return requestUri;
		}
	}

	private static String decodeAndCleanUriString(HttpServletRequest request, String uri)
	{
		uri = decodeRequestString(request, uri);
		int semicolonIndex = uri.indexOf(';');
		return (semicolonIndex != -1 ? uri.substring(0, semicolonIndex) : uri);
	}

	@SuppressWarnings("deprecation")
	public static String decodeRequestString(HttpServletRequest request, String source)
	{
		String enc = DEFAULT_CHARACTER_ENCODING;
		try
		{
			return URLDecoder.decode(source, enc);
		}
		catch (UnsupportedEncodingException ex)
		{
			if (log.isWarnEnabled())
			{
				log.warn("Could not decode request string [" + source + "] with encoding '" + enc
				        + "': falling back to platform default encoding; exception message: " + ex.getMessage());
			}
			return URLDecoder.decode(source);
		}
	}

	public static String normalize(String path)
	{
		return normalize(path, true);
	}

	private static String normalize(String path, boolean replaceBackSlash)
	{

		if (path == null)
			return null;

		// Create a place for the normalized path
		String normalized = path;

		if (replaceBackSlash && normalized.indexOf('\\') >= 0)
			normalized = normalized.replace('\\', '/');

		if (normalized.equals("/."))
			return "/";

		// Add a leading "/" if necessary
		if (!normalized.startsWith("/"))
			normalized = "/" + normalized;

		// Resolve occurrences of "//" in the normalized path
		while (true)
		{
			int index = normalized.indexOf("//");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + normalized.substring(index + 1);
		}

		// Resolve occurrences of "/./" in the normalized path
		while (true)
		{
			int index = normalized.indexOf("/./");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + normalized.substring(index + 2);
		}

		// Resolve occurrences of "/../" in the normalized path
		while (true)
		{
			int index = normalized.indexOf("/../");
			if (index < 0)
				break;
			if (index == 0)
				return (null);  // Trying to go outside our context
			int index2 = normalized.lastIndexOf('/', index - 1);
			normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
		}

		// Return the normalized path that we have completed
		return (normalized);

	}
}
