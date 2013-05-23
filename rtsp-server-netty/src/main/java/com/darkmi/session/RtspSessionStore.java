package com.darkmi.session;

/**
 * 负责创建和管理<code>session</code>.
 */
public interface RtspSessionStore {

	/**
	 * 使用指定的key创建一个session.
	 * 如果key已将使用则返回<code>null</code>,表示需要使用其他key.
	 *
	 * @param key  新session所使用的key
	 * @return     新创建的session, 或如果指定key已经存在则返回<code>null</code>
	 */
	public RtspSession newSession(String key);

	/**
	 * 使用指定key获取一个已经存在的session.
	 * 所有RtspSessionStore的实现需要对session的失效时间进行管理,
	 * 也就是说在返回session之前应该对其进行访问标记.
	 *
	 * @param key  获取session所需要的key
	 * @return     获取到的session,如果没有指定key的session则返回<code>null</code>
	 */
	public RtspSession locateSession(String key);

	/**
	 * 为<code>SessionStore</code>添加一个监听器.
	 *
	 * @param listener  监听器
	 */
	public void addSessionListener(RtspSessionListener listener);

	/**
	 * 关闭store,相关资源将被关闭.
	 */
	public void close();

}
