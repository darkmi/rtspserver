package com.darkmi.session;

/**
 * A connectionless transport can recycle existing sessions by assigning an
 * {@link RtspSessionRecycler} to an {@link IoService}.
 * 
 *
 */
public interface RtspSessionRecycler {
	/**
	 * A dummy recycler that doesn't recycle any sessions.  Using this recycler will
	 * make all session lifecycle events to be fired for every I/O for all connectionless
	 * sessions.
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
	 * Called when the underlying transport creates or writes a new {@link RtspSession}.
	 *
	 * @param session
	 *            the new {@link RtspSession}.
	 */
	void put(RtspSession session);

	/**
	 * Attempts to retrieve a recycled {@link RtspSession}.
	 *
	 * @param localAddress
	 *            the local socket address of the {@link RtspSession} the
	 *            transport wants to recycle.
	 * @param remoteAddress
	 *            the remote socket address of the {@link RtspSession} the
	 *            transport wants to recycle.
	 * @return a recycled {@link RtspSession}, or null if one cannot be found.
	 */
	RtspSession recycle(String sessionKey);

	/**
	 * Called when an {@link RtspSession} is explicitly closed.
	 *
	 * @param session
	 *            the new {@link RtspSession}.
	 */
	void remove(RtspSession session);
}
