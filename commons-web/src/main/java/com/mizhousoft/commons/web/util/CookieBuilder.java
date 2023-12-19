package com.mizhousoft.commons.web.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

/**
 * CookieBuilder
 *
 */
public abstract class CookieBuilder
{
	public static final int DEFAULT_MAX_AGE = -1;

	public static final int DEFAULT_VERSION = -1;

	protected static final String NAME_VALUE_DELIMITER = "=";

	protected static final String ATTRIBUTE_DELIMITER = "; ";

	protected static final long DAY_MILLIS = 86400000; // 1 day = 86,400,000 milliseconds

	protected static final String GMT_TIME_ZONE_ID = "GMT";

	protected static final String COOKIE_DATE_FORMAT_STRING = "EEE, dd-MMM-yyyy HH:mm:ss z";

	protected static final String COOKIE_HEADER_NAME = "Set-Cookie";

	protected static final String PATH_ATTRIBUTE_NAME = "Path";

	protected static final String EXPIRES_ATTRIBUTE_NAME = "Expires";

	protected static final String MAXAGE_ATTRIBUTE_NAME = "Max-Age";

	protected static final String DOMAIN_ATTRIBUTE_NAME = "Domain";

	protected static final String VERSION_ATTRIBUTE_NAME = "Version";

	protected static final String COMMENT_ATTRIBUTE_NAME = "Comment";

	protected static final String SECURE_ATTRIBUTE_NAME = "Secure";

	protected static final String HTTP_ONLY_ATTRIBUTE_NAME = "HttpOnly";

	protected static final String SAME_SITE_ATTRIBUTE_NAME = "SameSite";

	public static enum SameSiteOptions
	{
		NONE,

		LAX,

		STRICT,
	}

	public static String build(String name, String value, String domain, String path, boolean secure, boolean httpOnly)
	{
		return build(name, value, null, domain, path, DEFAULT_MAX_AGE, DEFAULT_VERSION, secure, httpOnly, SameSiteOptions.LAX);
	}

	public static String build(String name, String value, String domain, String path, int maxAge, boolean secure, boolean httpOnly,
	        SameSiteOptions sameSite)
	{
		return build(name, value, null, domain, path, maxAge, DEFAULT_VERSION, secure, httpOnly, sameSite);
	}

	public static String build(String name, String value, String comment, String domain, String path, int maxAge, int version,
	        boolean secure, boolean httpOnly, SameSiteOptions sameSite)
	{
		StringBuilder sb = new StringBuilder(name).append(NAME_VALUE_DELIMITER);

		if (!StringUtils.isBlank(value))
		{
			sb.append(value);
		}

		appendComment(sb, comment);
		appendDomain(sb, domain);
		appendPath(sb, path);
		appendExpires(sb, maxAge);
		appendVersion(sb, version);
		appendSecure(sb, secure);
		appendHttpOnly(sb, httpOnly);
		appendSameSite(sb, sameSite);

		return sb.toString();

	}

	private static void appendComment(StringBuilder sb, String comment)
	{
		if (!StringUtils.isBlank(comment))
		{
			sb.append(ATTRIBUTE_DELIMITER);
			sb.append(COMMENT_ATTRIBUTE_NAME).append(NAME_VALUE_DELIMITER).append(comment);
		}
	}

	private static void appendDomain(StringBuilder sb, String domain)
	{
		if (!StringUtils.isBlank(domain))
		{
			sb.append(ATTRIBUTE_DELIMITER);
			sb.append(DOMAIN_ATTRIBUTE_NAME).append(NAME_VALUE_DELIMITER).append(domain);
		}
	}

	private static void appendPath(StringBuilder sb, String path)
	{
		if (!StringUtils.isBlank(path))
		{
			sb.append(ATTRIBUTE_DELIMITER);
			sb.append(PATH_ATTRIBUTE_NAME).append(NAME_VALUE_DELIMITER).append(path);
		}
	}

	private static void appendExpires(StringBuilder sb, int maxAge)
	{
		if (maxAge >= 0)
		{
			sb.append(ATTRIBUTE_DELIMITER);
			sb.append(MAXAGE_ATTRIBUTE_NAME).append(NAME_VALUE_DELIMITER).append(maxAge);
			sb.append(ATTRIBUTE_DELIMITER);
			Date expires;
			if (maxAge == 0)
			{
				// delete the cookie by specifying a time in the past (1 day ago):
				expires = new Date(System.currentTimeMillis() - DAY_MILLIS);
			}
			else
			{
				// Value is in seconds. So take 'now' and add that many seconds, and that's our
				// expiration date:
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.SECOND, maxAge);
				expires = cal.getTime();
			}
			String formatted = toCookieDate(expires);
			sb.append(EXPIRES_ATTRIBUTE_NAME).append(NAME_VALUE_DELIMITER).append(formatted);
		}
	}

	private static void appendVersion(StringBuilder sb, int version)
	{
		if (version > DEFAULT_VERSION)
		{
			sb.append(ATTRIBUTE_DELIMITER);
			sb.append(VERSION_ATTRIBUTE_NAME).append(NAME_VALUE_DELIMITER).append(version);
		}
	}

	private static void appendSecure(StringBuilder sb, boolean secure)
	{
		if (secure)
		{
			sb.append(ATTRIBUTE_DELIMITER);
			sb.append(SECURE_ATTRIBUTE_NAME); // No value for this attribute
		}
	}

	private static void appendHttpOnly(StringBuilder sb, boolean httpOnly)
	{
		if (httpOnly)
		{
			sb.append(ATTRIBUTE_DELIMITER);
			sb.append(HTTP_ONLY_ATTRIBUTE_NAME); // No value for this attribute
		}
	}

	private static void appendSameSite(StringBuilder sb, SameSiteOptions sameSite)
	{
		if (sameSite != null)
		{
			sb.append(ATTRIBUTE_DELIMITER);
			sb.append(SAME_SITE_ATTRIBUTE_NAME).append(NAME_VALUE_DELIMITER).append(sameSite.toString().toLowerCase(Locale.ENGLISH));
		}
	}

	private static String toCookieDate(Date date)
	{
		TimeZone tz = TimeZone.getTimeZone(GMT_TIME_ZONE_ID);
		DateFormat fmt = new SimpleDateFormat(COOKIE_DATE_FORMAT_STRING, Locale.US);
		fmt.setTimeZone(tz);
		return fmt.format(date);
	}
}
