package com.darkmi.server.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.darkmi.server.core.RtspController;

@Configuration
@ComponentScan(basePackages = { "com.darkmi" })
@PropertySource("classpath:application.properties")
public class ServerConfig {
	public static final Logger logger = Logger.getLogger(ServerConfig.class);
	private static final String PROPERTY_RTSP_SERVER_IP = "rtsp.server.ip";
	private static final String PROPERTY_RTSP_SERVER_PORT = "rtsp.server.port";

	@Autowired
	private Environment environment;

	@Bean(autowire = Autowire.BY_TYPE)
	public RtspController rtspServer() {
		String ip = environment.getProperty(PROPERTY_RTSP_SERVER_IP);
		int port = Integer.parseInt(environment.getProperty(PROPERTY_RTSP_SERVER_PORT));

		RtspController rtspController = null;
		try {
			rtspController = new RtspController();
			rtspController.setIp(ip);
			rtspController.setPort(port);
		} catch (Exception e) {
			logger.error("Create RtspServer Error.........", e);
		}
		return rtspController;
	}
	
	public String getIp(){
		return environment.getProperty(PROPERTY_RTSP_SERVER_IP);
	}
	
	public int getPort(){
		return Integer.parseInt(environment.getProperty(PROPERTY_RTSP_SERVER_PORT));
	}
}