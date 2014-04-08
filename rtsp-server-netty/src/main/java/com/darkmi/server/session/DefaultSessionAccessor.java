package com.darkmi.server.session;

public class DefaultSessionAccessor implements RtspSessionAccessor {
  // private static final Logger logger = LoggerFactory.getLogger(DefaultSessionAccessor.class);
  private RtspSessionStore store;

  public DefaultSessionAccessor() {
    if (store == null) {
      store = new BasicSessionStore();
    }
  }

  @Override
  public RtspSession getSession(String sessionKey, boolean create) {
    return store.newSession(sessionKey, create);
  }

  @Override
  public void dispose() {
    store.close();
  }

  /* ~~~~~~~~~~~ Setters ~~~~~~~~~~~~~~~~~ */

  /**
   * Sets the session store employed by this accessor
   * 
   * @param store The store
   */
  public void setSessionStore(RtspSessionStore store) {
    if (this.store != null) {
      this.store.close();
    }
    this.store = store;
  }
}
