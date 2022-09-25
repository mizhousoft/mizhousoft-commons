package com.mizhousoft.commons.web.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.mizhousoft.commons.context.support.ApplicationContextRegistration;

/**
 * 事件监听器
 *
 * @version
 */
@Component
public class ApplicationContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>
{
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationContextRefreshedListener.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		ApplicationContextRegistration.registerApplicationContext(event.getApplicationContext());

		LOG.info("ApplicationContextRefreshedListener handle event successfully.");
	}
}