package com.mizhousoft.commons.web.i18n.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.context.support.ServletContextResource;

import com.mizhousoft.commons.web.i18n.I18nWebException;
import com.mizhousoft.commons.web.i18n.constant.Language;
import com.mizhousoft.commons.web.i18n.domain.MessageResourceBundle;
import com.mizhousoft.commons.web.i18n.internal.ResourceBundleRegistry;

/**
 * WebApp AppLocalization Loader
 * 
 * @version
 */
public class WebAppI18nResourceLoader
{
	private static final Logger LOG = LoggerFactory.getLogger(WebAppI18nResourceLoader.class);

	/**
	 * 加载国际化资源
	 * 
	 * @param servletContext
	 * @param i18nConfigLocation
	 * @throws I18nWebException
	 */
	public static void load(ServletContext servletContext, String i18nConfigLocation) throws I18nWebException
	{
		if (StringUtils.isBlank(i18nConfigLocation))
		{
			return;
		}

		List<MessageResourceBundle> resourceBundles = new ArrayList<MessageResourceBundle>();

		Language[] languages = { Language.EN, Language.ZH };
		for (Language language : languages)
		{
			String localeString = language.getValue();
			String path = i18nConfigLocation + "_" + localeString + ".properties";

			InputStreamSource istreamSource = new ServletContextResource(servletContext, path);

			try (InputStream istream = istreamSource.getInputStream())
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