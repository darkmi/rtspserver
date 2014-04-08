package com.darkmi.server.session;


public class BasicSessionListener implements RtspSessionListener {
  // private static final Logger logger = LoggerFactory.getLogger(BasicSessionListener.class);

  @Override
  public void sessionCreated(RtspSession rtspSession) {}

  @Override
  public void sessionDestroyed(RtspSession rtspSession) {}

  @Override
  public void sessionExpired(RtspSession rtspSession) {}
}
