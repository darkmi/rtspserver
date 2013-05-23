package com.darkmi.session;

import org.apache.mina.core.RuntimeIoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <code>RtspSessionStore</code>接口的实现类.
 */
public class BasicSessionStore implements RtspSessionStore {
	private static final Logger logger = LoggerFactory.getLogger(BasicSessionStore.class);
	private static final RtspSessionRecycler DEFAULT_RECYCLER = new ExpiringSessionRecycler();
	private RtspSessionRecycler sessionRecycler = DEFAULT_RECYCLER;
	private final Object bindLock = new Object();
	/*正在关闭*/
	private volatile boolean disposing;
	/*已经关闭*/
	private volatile boolean disposed;

	/**
	 *  使用指定sessionKey创建一个session.
	 */
	public RtspSession newSession(String sessionKey) {
		logger.debug("创建session ...");
		if (isDisposing()) {
			throw new IllegalStateException("Already disposed.");
		}

		if (sessionKey == null) {
			throw new IllegalArgumentException("remoteAddress");
		}

		synchronized (bindLock) {
			try {
				return newSessionWithoutLock(sessionKey);
			} catch (RuntimeException e) {
				throw e;
			} catch (Error e) {
				throw e;
			} catch (Exception e) {
				throw new RuntimeIoException("Failed to create a session.", e);
			}
		}
	}

	private RtspSession newSessionWithoutLock(String sessionKey) throws Exception {

		RtspSession session;
		RtspSessionRecycler sessionRecycler = getSessionRecycler();

		synchronized (sessionRecycler) {
			session = sessionRecycler.recycle(sessionKey);

			if (session != null) {
				return session;
			}

			// If a new session needs to be created.
			BaseSession newSession = new BaseSession(sessionKey);
			getSessionRecycler().put(newSession);
			session = newSession;
		}
		return session;
	}
	
	public RtspSession newSession(String sessionKey, boolean create){
		
		return null;
		
	}

	@Override
	public RtspSession locateSession(String key) {
		return null;
	}

	@Override
	public void close() {

	}

	public RtspSessionRecycler getSessionRecycler() {
		return sessionRecycler;
	}

	public void setSessionRecycler(RtspSessionRecycler sessionRecycler) {
		synchronized (bindLock) {

			if (sessionRecycler == null) {
				sessionRecycler = DEFAULT_RECYCLER;
			}

			this.sessionRecycler = sessionRecycler;
		}
	}

	public final boolean isDisposing() {
		return disposing;
	}

	public final boolean isDisposed() {
		return disposed;
	}
	
	/**
	 * 不做任何事情.
	 */
	@Override
	public void addSessionListener(RtspSessionListener listener) {		
	}

}
