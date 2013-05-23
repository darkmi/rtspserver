package com.darkmi.session;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultIoSessionAttributeMap implements RtspSessionAttributeMap {

	private final Map<Object, Object> attributes = new ConcurrentHashMap<Object, Object>(4);

	/**
	 * Default constructor
	 */
	public DefaultIoSessionAttributeMap() {
		super();
	}

	public Object getAttribute(RtspSession session, Object key, Object defaultValue) {
		if (key == null) {
			throw new IllegalArgumentException("key");
		}

		Object answer = attributes.get(key);
		if (answer == null) {
			return defaultValue;
		}

		return answer;
	}

	public Object setAttribute(RtspSession session, Object key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException("key");
		}

		if (value == null) {
			return attributes.remove(key);
		}

		return attributes.put(key, value);
	}

	public Object setAttributeIfAbsent(RtspSession session, Object key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException("key");
		}

		if (value == null) {
			return null;
		}

		Object oldValue;
		synchronized (attributes) {
			oldValue = attributes.get(key);
			if (oldValue == null) {
				attributes.put(key, value);
			}
		}
		return oldValue;
	}

	public Object removeAttribute(RtspSession session, Object key) {
		if (key == null) {
			throw new IllegalArgumentException("key");
		}

		return attributes.remove(key);
	}

	public boolean removeAttribute(RtspSession session, Object key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException("key");
		}

		if (value == null) {
			return false;
		}

		synchronized (attributes) {
			if (value.equals(attributes.get(key))) {
				attributes.remove(key);
				return true;
			}
		}

		return false;
	}

	public boolean replaceAttribute(RtspSession session, Object key, Object oldValue, Object newValue) {
		synchronized (attributes) {
			Object actualOldValue = attributes.get(key);
			if (actualOldValue == null) {
				return false;
			}

			if (actualOldValue.equals(oldValue)) {
				attributes.put(key, newValue);
				return true;
			}

			return false;
		}
	}

	public boolean containsAttribute(RtspSession session, Object key) {
		return attributes.containsKey(key);
	}

	public Set<Object> getAttributeKeys(RtspSession session) {
		synchronized (attributes) {
			return new HashSet<Object>(attributes.keySet());
		}
	}

	public void dispose(RtspSession session) throws Exception {
		// Do nothing
	}
}

