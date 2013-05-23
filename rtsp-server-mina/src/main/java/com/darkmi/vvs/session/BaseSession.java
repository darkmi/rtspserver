package com.darkmi.vvs.session;

import java.util.Set;

/**
 * <code>RtspSession</code>的一个实现.
 */
public class BaseSession implements RtspSession {

	/**session标识符*/
	private String id;

	/**session维护者*/
	private BasicSessionStore owner;

	/** session的创建时间 */
	private final long creationTime;

	/**
	 * 存储所有用户自定义的attributes.
	 */
	private RtspSessionAttributeMap attributes = new DefaultIoSessionAttributeMap();

	/**
	 * 构造函数.
	 * @param owner  用于维护该session的BasicSessionStore.
	 */
	BaseSession(String id, BasicSessionStore owner) {
		this.id = id;
		this.owner = owner;
		this.creationTime = System.currentTimeMillis();
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
	@Override
	public Object getAttribute(Object key) {
		return getAttribute(key, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getAttribute(Object key, Object defaultValue) {
		return attributes.getAttribute(key, defaultValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object setAttribute(Object key, Object value) {
		return attributes.setAttribute(key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object setAttribute(Object key) {
		return setAttribute(key, Boolean.TRUE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object setAttributeIfAbsent(Object key, Object value) {
		return attributes.setAttributeIfAbsent(key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object setAttributeIfAbsent(Object key) {
		return setAttributeIfAbsent(key, Boolean.TRUE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object removeAttribute(Object key) {
		return attributes.removeAttribute(this, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeAttribute(Object key, Object value) {
		return attributes.removeAttribute(key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean replaceAttribute(Object key, Object oldValue, Object newValue) {
		return attributes.replaceAttribute(key, oldValue, newValue);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsAttribute(Object key) {
		return attributes.containsAttribute(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Object> getAttributeKeys() {
		return attributes.getAttributeKeys();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getCreationTime() {
		return creationTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public void destroy() {
		owner.destory(this);
	}

	/**
	 * 
	 */
	public RtspSessionAttributeMap getAttributeMap() {
		return attributes;
	}

}
