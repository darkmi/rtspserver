package com.darkmi.rtsp.server.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class DefaultWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext appContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(DefaultAppConfig.class);
		appContext.addListener(new ContextLoaderListener(rootContext));
		Dynamic dispatcher = appContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}
}
