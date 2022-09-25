package com.mizhousoft.commons.web.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.commons.data.NestedRuntimeException;

/**
 * 公共上下文监听器
 *
 * @version
 */
public class CommonContextLoaderListener implements ServletContextListener
{
	private static final Logger LOG = LoggerFactory.getLogger(CommonContextLoaderListener.class);

	/**
	 * 初始化
	 * 
	 * @param sce
	 */
	@Override
	public final void contextInitialized(ServletContextEvent sce)
	{
		try
		{
			LOG.info("Start to initialize webapp context.");

			ServletContext servletContext = sce.getServletContext();

			InnerServletContextHolder.setServletContext(servletContext);

			// 调用扩展接口
			doContextInitialized(sce);

			LOG.info("Initialize webapp context successfully.");
		}
		catch (Throwable e)
		{
			LOG.error("Initialize webapp context failed.", e);
			throw new NestedRuntimeException("Initialize webapp context failed.", e);
		}
	}

	/**
	 * 扩展接口
	 * 
	 * @param sce
	 */
	protected void doContextInitialized(ServletContextEvent sce)
	{

	}

	/**
	 * 销毁
	 * 
	 * @param sce
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{

	}
}
