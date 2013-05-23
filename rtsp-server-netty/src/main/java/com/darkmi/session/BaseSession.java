package com.darkmi.session;

import java.util.Set;

/**
 * IoSession的一个实现.
 * 
 */
public class BaseSession implements RtspSession {

	/** The session ID */
	private String id;

	/**
	 * 存储所有用户自定义的attributes.
	 */
	private static RtspSessionAttributeMap attributes = new DefaultIoSessionAttributeMap();

	/** The Session creation's time */
	private final long creationTime;

	/**
	 * 构造函数.
	 */
	protected BaseSession(String id) {
		// Initialize all the Session counters to the current time
		long currentTime = System.currentTimeMillis();
		creationTime = currentTime;
		// Set a new ID for this session
		this.id = id;
	}

	/**
	 * @return  session标识符
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Object getAttribute(Object key) {
		return getAttribute(key, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Object getAttribute(Object key, Object defaultValue) {
		return attributes.getAttribute(this, key, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Object setAttribute(Object key, Object value) {
		return attributes.setAttribute(this, key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Object setAttribute(Object key) {
		return setAttribute(key, Boolean.TRUE);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Object setAttributeIfAbsent(Object key, Object value) {
		return attributes.setAttributeIfAbsent(this, key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Object setAttributeIfAbsent(Object key) {
		return setAttributeIfAbsent(key, Boolean.TRUE);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Object removeAttribute(Object key) {
		return attributes.removeAttribute(this, key);
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean removeAttribute(Object key, Object value) {
		return attributes.removeAttribute(this, key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean replaceAttribute(Object key, Object oldValue, Object newValue) {
		return attributes.replaceAttribute(this, key, oldValue, newValue);
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean containsAttribute(Object key) {
		return attributes.containsAttribute(this, key);
	}

	/**
	 * {@inheritDoc}
	 */
	public final Set<Object> getAttributeKeys() {
		return attributes.getAttributeKeys(this);
	}

	/**
	 * 
	 */
	public final RtspSessionAttributeMap getAttributeMap() {
		return attributes;
	}

	/**
	 * {@inheritDoc}
	 */
	public final long getCreationTime() {
		return creationTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return super.hashCode();
	}

	/**
	 * 
	 */
	@Override
	public final boolean equals(Object o) {
		return super.equals(o);
	}
}
