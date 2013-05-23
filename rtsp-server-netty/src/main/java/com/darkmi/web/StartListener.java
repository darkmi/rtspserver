package com.darkmi.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.darkmi.vvs.core.RtspController;


/**
 * (1)启动vvs 554端口;<br>
 * (2)启动vvs 8060端口.
 */
public class StartListener implements ServletContextListener {
	private static Logger logger = LoggerFactory.getLogger(StartListener.class);

	/**
	 * 关闭应用.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
			RtspController videoServer = (RtspController) context.getBean("videoServer");
			videoServer.stop();
			logger.debug("VideoServer关闭。。。。。。。。。。。");
		} catch (Exception e) {
			logger.error("VideoServer监听接口关闭错误", e);
		}
	}

	/**
	 * 启动应用.
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
			RtspController videoServer = (RtspController) context.getBean("vvs");
			videoServer.start();
			logger.debug("VideoServer监听接口启动。。。。。。。。。。。");
		} catch (Exception e) {
			logger.error("VideoServer监听接口启动错误", e);
		}

		//		try {
		//			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		//			A3Controller apm = (A3Controller) context.getBean("apm");
		//			apm.start();
		//			logger.debug("apm监听接口启动。。。。。。。。。。。");
		//		} catch (Exception e) {
		//			logger.error("apm监听接口启动错误", e);
		//		}

	}

}
