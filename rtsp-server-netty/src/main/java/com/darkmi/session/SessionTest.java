package com.darkmi.session;

import org.apache.mina.core.RuntimeIoException;

public class SessionTest {

	private static final RtspSessionRecycler DEFAULT_RECYCLER = new ExpiringSessionRecycler();
	private RtspSessionRecycler sessionRecycler = DEFAULT_RECYCLER;

	/**
	 * A lock object which must be acquired when related resources are
	 * destroyed.
	 */
	protected final Object disposalLock = new Object();

	private volatile boolean disposing;

	private volatile boolean disposed;

	/**
	 * The lock object which is acquired while bind or unbind operation is performed.
	 * Acquire this lock in your property setters which shouldn't be changed while
	 * the service is bound.
	 */
	protected final Object bindLock = new Object();

	/**
	 * 
	 */
	public RtspSession newSession(String sessionKey) {
		//if (isDisposing()) {
		//	throw new IllegalStateException("Already disposed.");
		//}

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

	public static void main(String[] args) {
		
		SessionTest sessionTest = new SessionTest();
		
		sessionTest.newSession("aaa");

	}

}
