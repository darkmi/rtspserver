package com.darkmi.server.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicSessionStore implements RtspSessionStore {
  private static final Logger logger = LoggerFactory.getLogger(BasicSessionStore.class);
  private RtspSessionRecycler sessionRecycler = new ExpiringSessionRecycler();
  private final Object lock = new Object();
  private volatile boolean disposing;
  private volatile boolean disposed;

  @Override
  public RtspSession newSession(String sessionKey, boolean create) {
    logger.debug("创建session ...");
    if (isDisposing()) {
      throw new IllegalStateException("Already disposed.");
    }

    if (sessionKey == null) {
      throw new IllegalArgumentException("remoteAddress");
    }

    synchronized (lock) {
      try {
        return newSessionWithoutLock(sessionKey, create);
      } catch (RuntimeException e) {
        throw e;
      } catch (Error e) {
        throw e;
      } catch (Exception e) {
        // throw new RuntimeIoException("Failed to create a session.", e);
      }
    }
    return null;
  }

  private RtspSession newSessionWithoutLock(String sessionKey, boolean create) throws Exception {

    RtspSession session;
    RtspSessionRecycler sessionRecycler = getSessionRecycler();

    synchronized (sessionRecycler) {
      session = sessionRecycler.recycle(sessionKey);
      if (null == session && true == create) {
        BaseSession newSession = new BaseSession(sessionKey, this);
        getSessionRecycler().put(newSession);
        session = newSession;
      }
    }
    return session;
  }

  @Override
  public void close() {

  }

  public void destory(RtspSession session) {
    sessionRecycler.remove(session);
  }

  @Override
  public void addSessionListener(RtspSessionListener listener) {}

  /*-----------   Setters And Getters   --------------*/

  public RtspSessionRecycler getSessionRecycler() {
    return sessionRecycler;
  }

  // public void setSessionRecycler(RtspSessionRecycler sessionRecycler) {
  // synchronized (lock) {
  // if (sessionRecycler == null) {
  // sessionRecycler = DEFAULT_RECYCLER;
  // }
  // this.sessionRecycler = sessionRecycler;
  // }
  // }

  public boolean isDisposing() {
    return disposing;
  }

  public boolean isDisposed() {
    return disposed;
  }

}
