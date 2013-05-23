package com.darkmi.vvs.session;

/**
 * 一个<code>Session</code>创建和访问的门面模式实现.一个<code>SessionAccessor</code>
 * 提供了一个访问(如果需要可以创建)与请求相关的session的简易接口.
 */
public interface RtspSessionAccessor {

	/** 
	 * 使用指定sessionKey获取一个session.如果无法使用指定sessionKey获取到session,
	 * 且<code>create</code>为<code>true</code>,则一个新session被创建,并绑定到对
	 * 应的请求中,否则放回null.
	 * 
	 * @param create   如果指定sessionkey的session不存在,且该参数为true则创建一个session.
	 *                 
	 * @return         分配或创建的session.如果指定sessionKey的session不存在,
	 *                 且<code>create</code>为<code>false</code>则返回</code>null</code>.
	 */
	public RtspSession getSession(String sessionKey, boolean create);

	/**
	 * 销毁该accessor,释放所有相关资源.
	 */
	public void dispose();
}
