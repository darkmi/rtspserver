package com.darkmi.session;

/**
 * An {@link RtspSessionRecycler} with sessions that time out on inactivity.
 */
public class ExpiringSessionRecycler implements RtspSessionRecycler {
	
	private ExpiringMap<Object, RtspSession> sessionMap;

	private ExpiringMap<Object, RtspSession>.Expirer mapExpirer;
	
	RtspSessionKeyFactory keyFactory = new SimpleRandomKeyFactory();

	public ExpiringSessionRecycler() {
		this(ExpiringMap.DEFAULT_TIME_TO_LIVE);
	}

	public ExpiringSessionRecycler(int timeToLive) {
		this(timeToLive, ExpiringMap.DEFAULT_EXPIRATION_INTERVAL);
	}

	public ExpiringSessionRecycler(int timeToLive, int expirationInterval) {
		sessionMap = new ExpiringMap<Object, RtspSession>(timeToLive, expirationInterval);
		mapExpirer = sessionMap.getExpirer();
		sessionMap.addExpirationListener(new DefaultExpirationListener());
	}

	public void put(RtspSession session) {
		mapExpirer.startExpiringIfNotStarted();

		Object key = generateKey(session);

		if (!sessionMap.containsKey(key)) {
			sessionMap.put(key, session);
		}
	}

	public RtspSession recycle(String key) {
		return sessionMap.get(key);
	}

	public void remove(RtspSession session) {
		sessionMap.remove(generateKey(session));
	}

	public void stopExpiring() {
		mapExpirer.stopExpiring();
	}

	public int getExpirationInterval() {
		return sessionMap.getExpirationInterval();
	}

	public int getTimeToLive() {
		return sessionMap.getTimeToLive();
	}

	public void setExpirationInterval(int expirationInterval) {
		sessionMap.setExpirationInterval(expirationInterval);
	}

	public void setTimeToLive(int timeToLive) {
		sessionMap.setTimeToLive(timeToLive);
	}

	private Object generateKey(RtspSession session) {
		return keyFactory.createSessionKey();
	}

	private class DefaultExpirationListener implements ExpirationListener<RtspSession> {
		public void expired(RtspSession expiredSession) {
			//expiredSession.close(true);
			System.out.println("失效监听器激活.");
		}
	}
}
