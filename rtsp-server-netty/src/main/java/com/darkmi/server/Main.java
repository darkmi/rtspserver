package com.darkmi.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.darkmi.server.config.ServerConfig;
import com.darkmi.server.core.RtspController;

public class Main {
  private static Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx =
        new AnnotationConfigApplicationContext(ServerConfig.class);
    RtspController rtspController = (RtspController) ctx.getBean(RtspController.class);
    try {
      rtspController.start();
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
  }
}
