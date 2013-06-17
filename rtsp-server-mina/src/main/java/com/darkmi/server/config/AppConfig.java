package com.darkmi.server.config;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.darkmi.server.RtspServer;

@Configuration
@ComponentScan(basePackages = { "com.darkmi" })
@PropertySource("classpath:application.properties")
public class AppConfig {
	private static Logger logger = LoggerFactory.getLogger(AppConfig.class);
	private static final String PROPERTY_RTSP_SERVER_IP = "rtsp.server.ip";
	private static final String PROPERTY_RTSP_SERVER_SETUP_PORT = "rtsp.server.setup.port";
	private static final String PROPERTY_RTSP_SERVER_PLAY_PORT = "rtsp.server.play.port";

	@Resource
	private Environment environment;

	@Bean
	public RtspServer rtspServer() {
		RtspServer rtspServer = new RtspServer();
		logger.debug("RTSP Server IP --> {}",environment.getRequiredProperty(PROPERTY_RTSP_SERVER_IP));
		//rtspServer.setIp(environment.getRequiredProperty(PROPERTY_RTSP_SERVER_IP));
		rtspServer.setSetupPort(Integer.parseInt(environment.getRequiredProperty(PROPERTY_RTSP_SERVER_SETUP_PORT)));
		rtspServer.setPlayPort(Integer.parseInt(environment.getRequiredProperty(PROPERTY_RTSP_SERVER_PLAY_PORT)));
		return rtspServer;
	}

}
