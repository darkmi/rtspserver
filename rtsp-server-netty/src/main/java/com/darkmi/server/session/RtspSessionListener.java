package com.darkmi.server.session;


/**
 * 接收session生命周期中的通知并进行处理.
 */
public interface RtspSessionListener {

  /**
   * session创建成功时激活该方法.
   * 
   * @param session 创建的session
   */
  public void sessionCreated(RtspSession rtspSession);

  /**
   * session销毁时激活该方法.
   * 
   * @param session 销毁的session
   */
  public void sessionDestroyed(RtspSession rtspSession);

  /**
   * session失效时激活该方法.
   * 
   * @param session 失效的session
   */
  public void sessionExpired(RtspSession rtspSession);

}
