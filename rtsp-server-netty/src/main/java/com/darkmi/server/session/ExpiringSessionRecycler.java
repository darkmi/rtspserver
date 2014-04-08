package com.darkmi.server.session;

public class ExpiringSessionRecycler implements RtspSessionRecycler {

  private ExpiringMap<String, RtspSession> sessionMap;

  private ExpiringMap<String, RtspSession>.Expirer mapExpirer;

  public ExpiringSessionRecycler() {
    this(ExpiringMap.DEFAULT_TIME_TO_LIVE);
  }

  public ExpiringSessionRecycler(int timeToLive) {
    this(timeToLive, ExpiringMap.DEFAULT_EXPIRATION_INTERVAL);
  }

  public ExpiringSessionRecycler(int timeToLive, int expirationInterval) {
    sessionMap = new ExpiringMap<String, RtspSession>(timeToLive, expirationInterval);
    mapExpirer = sessionMap.getExpirer();
    sessionMap.addExpirationListener(new DefaultExpirationListener());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void put(RtspSession session) {
    mapExpirer.startExpiringIfNotStarted();

    String key = session.getId();

    if (!sessionMap.containsKey(key)) {
      sessionMap.put(key, session);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RtspSession recycle(String key) {
    return sessionMap.get(key);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void remove(RtspSession session) {
    sessionMap.remove(session.getId());
  }

  public void stopExpiring() {
    mapExpirer.stopExpiring();
  }

  private class DefaultExpirationListener implements ExpirationListener<RtspSession> {
    public void expired(RtspSession expiredSession) {
      expiredSession.destroy();
      System.out.println(expiredSession.getId() + " --> 失效监听器激活.");
    }
  }

  /*-----------   Setters And Getters   --------------*/

  public int getExpirationInterval() {
    return sessionMap.getExpirationInterval();
  }

  public int getTimeToLive() {
    return sessionMap.getTimeToLive();
  }

  public void setExpirationInterval(int expirationInterval) {
    sessionMap.setExpirationInterval(expirationInterval);
  }

  public void setTimeToLive(int timeToLive) {
    sessionMap.setTimeToLive(timeToLive);
  }

}
