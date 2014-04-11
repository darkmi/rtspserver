package com.darkmi.server.session;

import org.apache.mina.core.RuntimeIoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>RtspSessionStore</code>接口的实现类.
 */
public class BasicSessionStore implements RtspSessionStore {
  /** 日记记录器 */
  private static final Logger logger = LoggerFactory.getLogger(BasicSessionStore.class);
  /** 负责回收超时的session */
  private RtspSessionRecycler sessionRecycler = new ExpiringSessionRecycler();
  /** 锁 */
  private final Object lock = new Object();
  /** 正在关闭 */
  private volatile boolean disposing;
  /** 已经关闭 */
  private volatile boolean disposed;

  // private static final RtspSessionRecycler DEFAULT_RECYCLER = new ExpiringSessionRecycler();

  /**
   * 使用指定sessionKey创建一个session.
   */
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
        throw new RuntimeIoException("Failed to create a session.", e);
      }
    }
  }

  private RtspSession newSessionWithoutLock(String sessionKey, boolean create) throws Exception {

    RtspSession session;
    RtspSessionRecycler sessionRecycler = getSessionRecycler();

    synchronized (sessionRecycler) {
      session = sessionRecycler.recycle(sessionKey);
      if (null == session && true == create) {
        BaseSession newSession = new BaseSession(sessionKey, this);
        // 将先创建的session维护起来
        getSessionRecycler().put(newSession);
        session = newSession;
      }
    }
    return session;
  }

  @Override
  public void close() {

  }

  /**
   * 销毁指定session.
   * 
   * @param session 准备销毁的session
   */
  public void destory(RtspSession session) {
    sessionRecycler.remove(session);
  }

  /**
   * 不做任何事情.
   */
  @Override
  public void addSessionListener(RtspSessionListener listener) {}

  /*-----------   Setters And Getters   --------------*/

  public RtspSessionRecycler getSessionRecycler() {
    return sessionRecycler;
  }

  public boolean isDisposing() {
    return disposing;
  }

  public boolean isDisposed() {
    return disposed;
  }

  // public void setSessionRecycler(RtspSessionRecycler sessionRecycler) {
  // synchronized (lock) {
  // if (sessionRecycler == null) {
  // sessionRecycler = DEFAULT_RECYCLER;
  // }
  // this.sessionRecycler = sessionRecycler;
  // }
  // }
}
