package com.mizhousoft.commons.web.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * CookieUtils
 *
 */
public class CookieUtils
{
	public static Cookie getCookie(HttpServletRequest request, String name)
	{
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length < 1)
		{
			return null;
		}

		for (Cookie c : cookies)
		{
			if (name.equals(c.getName()))
			{
				return c;
			}
		}

		return null;
	}

	public static String getCookieValue(HttpServletRequest request, String name)
	{
		Cookie cookie = getCookie(request, name);
		if (cookie != null)
		{
			return cookie.getValue();
		}

		return null;
	}

	public static Map<String, Cookie> getCookieMap(HttpServletRequest request)
	{
		Map<String, Cookie> cookieMap = new HashMap<>(10);

		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 1)
		{
			for (Cookie cookie : cookies)
			{
				cookieMap.put(cookie.getName(), cookie);
			}
		}

		return cookieMap;
	}

	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, Cookie cookie)
	{
		cookie.setPath("/");
		cookie.setValue("");
		cookie.setMaxAge(0);

		response.addCookie(cookie);
	}

	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name)
	{
		if (null == name)
		{
			return;
		}

		Cookie cookie = getCookie(request, name);
		if (null != cookie)
		{
			removeCookie(request, response, cookie);
		}
	}

	public static void removeAll(HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, Cookie> cookieMap = getCookieMap(request);

		Iterator<Entry<String, Cookie>> iter = cookieMap.entrySet().iterator();
		while (iter.hasNext())
		{
			Entry<String, Cookie> entry = iter.next();
			Cookie cookie = entry.getValue();

			removeCookie(request, response, cookie);
		}
	}
}
