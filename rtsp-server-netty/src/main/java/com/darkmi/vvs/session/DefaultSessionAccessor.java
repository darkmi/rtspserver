package com.darkmi.vvs.session;

import org.springframework.stereotype.Component;

/**
 * 管理SessionIdentifier,SessionKeyFactory,SessionStore的门面模式.
 */
@Component
public class DefaultSessionAccessor implements RtspSessionAccessor {
	//private static final Logger logger = LoggerFactory.getLogger(DefaultSessionAccessor.class);
	private RtspSessionStore store;

	/**
	 * 默认构造函数.
	 */
	public DefaultSessionAccessor() {
		//初始化store
		if (store == null) {
			//创建session store
			store = new BasicSessionStore();
		}
	}

	/**
	 * 获取RtspSession.
	 */
	@Override
	public RtspSession getSession(String sessionKey, boolean create) {
		return store.newSession(sessionKey, create);
	}

	/**
	 * 关闭 accessor. 直接关闭 store 即可.
	 */
	@Override
	public void dispose() {
		store.close();
	}

	/*~~~~~~~~~~~ Setters ~~~~~~~~~~~~~~~~~*/

	/**
	 * Sets the session store employed by this accessor
	 *
	 * @param store  The store
	 */
	public void setSessionStore(RtspSessionStore store) {
		if (this.store != null) {
			this.store.close();
		}
		this.store = store;
	}
}
