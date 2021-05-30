package com.mizhousoft.commons.web.i18n.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.commons.web.i18n.domain.ErrorCodeMessage;
import com.mizhousoft.commons.web.i18n.internal.ResourceBundleRegistry;

/**
 * Message Utils
 * 
 * @version
 */
abstract class InternalMessageUtils
{
	/**
	 * 获取Message
	 * 
	 * @param code
	 * @param args
	 * @param locale
	 * @return
	 */
	public static String getMessage(String code, Object[] args, Locale locale)
	{
		String localeString = locale.toString().toLowerCase(Locale.US);
		ResourceBundle resourceBundle = ResourceBundleRegistry.getMessageResourceBundle(localeString, code);
		if (null == resourceBundle)
		{
			return (code);
		}

		String value = resourceBundle.getString(code);
		if (StringUtils.isBlank(value))
		{
			return (code);
		}

		if (ArrayUtils.isNotEmpty(args))
		{
			value = MessageFormat.format(value, args);
		}

		return value;
	}

	/**
	 * 获取ErrorCodeMessage
	 * 
	 * @param errorCode
	 * @param args
	 * @param locale
	 * @return
	 */
	public static ErrorCodeMessage getErrorCodeMessage(String errorCode, Object[] args, Locale locale)
	{
		String descKey = errorCode + "." + "desc";
		String causeKeye = errorCode + "." + "cause";
		String solutionKey = errorCode + "." + "solution";

		String localeString = locale.toString();
		ResourceBundle resourceBundle = ResourceBundleRegistry.getMessageResourceBundle(localeString, descKey);
		if (null == resourceBundle)
		{
			return (new ErrorCodeMessage(errorCode, "!" + errorCode, "!" + errorCode, "!" + errorCode));
		}

		String desc = resourceBundle.getString(descKey);
		if (StringUtils.isBlank(desc))
		{
			return (new ErrorCodeMessage(errorCode, "!" + errorCode, "!" + errorCode, "!" + errorCode));
		}

		String cause = resourceBundle.getString(causeKeye);
		String solution = resourceBundle.getString(solutionKey);

		if (ArrayUtils.isNotEmpty(args))
		{
			desc = MessageFormat.format(desc, args);
		}

		return (new ErrorCodeMessage(errorCode, desc, cause, solution));
	}
}
