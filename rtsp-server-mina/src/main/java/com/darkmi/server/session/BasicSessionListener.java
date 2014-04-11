package com.darkmi.server.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicSessionListener implements RtspSessionListener {
  private static final Logger logger = LoggerFactory.getLogger(BasicSessionListener.class);

  @Override
  public void sessionCreated(RtspSession rtspSession) {
    logger.debug("session 创建 begin { ");
    logger.debug("session 创建 end } ");
  }

  @Override
  public void sessionDestroyed(RtspSession rtspSession) {
    logger.debug("session 销毁 begin { ");
    logger.debug("session 销毁 end } ");
  }

  @Override
  public void sessionExpired(RtspSession rtspSession) {
    logger.debug("session 失效处理begin { ");
    logger.debug("session 失效处理end } ");
  }
}
