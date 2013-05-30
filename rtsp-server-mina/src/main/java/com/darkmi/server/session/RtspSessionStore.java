package com.darkmi.server.session;

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
	public RtspSession newSession(String key, boolean create);

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
