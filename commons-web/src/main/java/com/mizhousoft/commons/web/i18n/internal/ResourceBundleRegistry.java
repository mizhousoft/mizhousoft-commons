package com.mizhousoft.commons.web.i18n.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.mizhousoft.commons.web.i18n.domain.MessageResourceBundle;

/**
 * Message BundleResource Registry
 * 
 * @version
 */
public class ResourceBundleRegistry
{
	// MessageResourceBundle容器
	private static Map<String, List<MessageResourceBundle>> resourceBundles = new ConcurrentHashMap<String, List<MessageResourceBundle>>(2);

	/**
	 * 注册ResourceBundle
	 * 
	 * @param bundleName
	 * @param messageResourceBundle
	 */
	public static void registerResources(MessageResourceBundle resourceBundle)
	{
		String locale = resourceBundle.getLocaleString();

		List<MessageResourceBundle> messageResourceBundles = resourceBundles.get(locale);
		if (null == messageResourceBundles)
		{
			messageResourceBundles = new ArrayList<MessageResourceBundle>(2);
			resourceBundles.put(locale, messageResourceBundles);
		}

		messageResourceBundles.add(resourceBundle);
	}

	/**
	 * 注销ResourceBundle
	 * 
	 * @param bundleName
	 */
	public static void unregisterResources(String bundleName)
	{
		removeResourceBundles(bundleName, resourceBundles);
	}

	/**
	 * 获取ResourceBundle
	 * 
	 * @param key
	 * @return
	 */
	public static ResourceBundle getMessageResourceBundle(String locale, String key)
	{
		List<MessageResourceBundle> messageResourceBundles = resourceBundles.get(locale);
		if (null == messageResourceBundles)
		{
			return null;
		}

		Iterator<MessageResourceBundle> iter = messageResourceBundles.iterator();
		while (iter.hasNext())
		{
			MessageResourceBundle messageResourceBundle = iter.next();
			if (messageResourceBundle.containsKey(key))
			{
				return messageResourceBundle;
			}
		}

		return null;
	}

	/**
	 * 清除所有的ResourceBundle
	 */
	public static synchronized void clearAll()
	{
		resourceBundles.clear();
	}

	/**
	 * 删除MessageResourceBundle
	 * 
	 * @param bundleName
	 * @param resourceBundleMap
	 */
	private static void removeResourceBundles(String bundleName, Map<String, List<MessageResourceBundle>> resourceBundleMap)
	{
		Iterator<Entry<String, List<MessageResourceBundle>>> iter = resourceBundleMap.entrySet().iterator();
		while (iter.hasNext())
		{
			Entry<String, List<MessageResourceBundle>> entry = iter.next();
			List<MessageResourceBundle> messageResourceBundles = entry.getValue();

			Iterator<MessageResourceBundle> resBundleIter = messageResourceBundles.iterator();
			while (resBundleIter.hasNext())
			{
				MessageResourceBundle resourceBundle = resBundleIter.next();
				if (StringUtils.equals(bundleName, resourceBundle.getBundleName()))
				{
					resBundleIter.remove();
					break;
				}
			}
		}
	}
}
