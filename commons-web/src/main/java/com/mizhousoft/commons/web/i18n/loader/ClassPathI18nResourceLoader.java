package com.mizhousoft.commons.web.i18n.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.mizhousoft.commons.web.i18n.I18nWebException;
import com.mizhousoft.commons.web.i18n.constant.Language;
import com.mizhousoft.commons.web.i18n.domain.MessageResourceBundle;
import com.mizhousoft.commons.web.i18n.internal.ResourceBundleRegistry;

/**
 * ClassPath下的国际化资源加载器
 * 
 * @version
 */
public class ClassPathI18nResourceLoader
{
	private static final Logger LOG = LoggerFactory.getLogger(ClassPathI18nResourceLoader.class);

	/**
	 * 加载资源
	 * 
	 * @param clazz
	 * @param i18nConfigLocation
	 */
	public static void load(Class<?> clazz, String i18nConfigLocation) throws I18nWebException
	{
		List<MessageResourceBundle> resourceBundles = new ArrayList<MessageResourceBundle>(2);

		Language[] languages = { Language.EN, Language.ZH };
		for (Language language : languages)
		{
			String localeString = language.getValue();
			String path = i18nConfigLocation + "_" + localeString + ".properties";

			Resource resource = new ClassPathResource(path, clazz);

			try (InputStream istream = resource.getInputStream())
			{
				MessageResourceBundle resourceBundle = new MessageResourceBundle(istream);
				resourceBundle.setLocaleString(localeString.toLowerCase(Locale.US));

				resourceBundles.add(resourceBundle);

				LOG.info("Load " + path + " resource successfully.");
			}
			catch (IOException e)
			{
				throw new I18nWebException("Load " + path + " resource failed.", e);
			}
		}

		Iterator<MessageResourceBundle> iter = resourceBundles.iterator();
		while (iter.hasNext())
		{
			MessageResourceBundle messageResourceBundle = iter.next();
			ResourceBundleRegistry.registerResources(messageResourceBundle);
		}
	}
}
