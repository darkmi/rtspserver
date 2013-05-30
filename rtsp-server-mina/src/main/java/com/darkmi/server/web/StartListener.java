package com.darkmi.server.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.darkmi.server.PlayServer;
import com.darkmi.server.SetupServer;


/**
 * (1)启动vvs 554端口;<br>
 * (2)启动vvs 8060端口.
 */
public class StartListener implements ServletContextListener {
	private static Logger logger = LoggerFactory.getLogger(StartListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
			SetupServer setupServer = (SetupServer) context.getBean("setupServer");
			setupServer.stop();
			logger.debug("SetupServer监听接口关闭。。。。。。。。。。。");
		} catch (Exception e) {
			logger.error("SetupServer监听接口关闭错误", e);
		}

		try {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
			PlayServer playServer = (PlayServer) context.getBean("playServer");
			playServer.stop();
			logger.debug("PlayServer监听接口关闭。。。。。。。。。。。");
		} catch (Exception e) {
			logger.error("PlayServer监听接口关闭错误", e);
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
			SetupServer setupServer = (SetupServer) context.getBean("setupServer");
			setupServer.start();
			logger.debug("SetupServer监听接口启动。。。。。。。。。。。");
		} catch (Exception e) {
			logger.error("SetupServer监听接口启动错误", e);
		}

		try {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
			PlayServer playServer = (PlayServer) context.getBean("playServer");
			playServer.start();
			logger.debug("PlayServer监听接口启动。。。。。。。。。。。");
		} catch (Exception e) {
			logger.error("PlayServer监听接口启动错误", e);
		}
	}

}
