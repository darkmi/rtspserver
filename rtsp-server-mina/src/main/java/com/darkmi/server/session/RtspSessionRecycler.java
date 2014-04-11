package com.darkmi.server.session;

/**
 * A connectionless transport can recycle existing sessions by assigning an
 * {@link RtspSessionRecycler} to an {@link IoService}.
 */
public interface RtspSessionRecycler {
  /**
   * A dummy recycler that doesn't recycle any sessions. Using this recycler will make all session
   * lifecycle events to be fired for every I/O for all connectionless sessions.
   */
  static RtspSessionRecycler NOOP = new RtspSessionRecycler() {
    public void put(RtspSession session) {
      // Do nothing
    }

    public RtspSession recycle(String sessionKey) {
      return null;
    }

    public void remove(RtspSession session) {
      // Do nothing
    }
  };

  /**
   * 将新创建的{@link RtspSession}纳入失效管理之中.
   * 
   * @param session the new {@link RtspSession}.
   */
  void put(RtspSession session);

  /**
   * 从失效管理的sessionMap中获取一个{@link RtspSession},并更新期最后访问时间.
   * 
   * @return a recycled {@link RtspSession}, or null if one cannot be found.
   * 
   */
  RtspSession recycle(String sessionKey);

  /**
   * 从失效管理的sessionMap中移除一个{@link RtspSession}.
   * 
   * @param session the new {@link RtspSession}.
   */
  void remove(RtspSession session);
}
