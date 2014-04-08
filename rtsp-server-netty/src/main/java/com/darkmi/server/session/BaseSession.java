package com.darkmi.server.session;

import java.util.Set;

public class BaseSession implements RtspSession {

  private String id;
  private BasicSessionStore owner;
  private final long creationTime;

  private RtspSessionAttributeMap attributes = new DefaultIoSessionAttributeMap();

  BaseSession(String id, BasicSessionStore owner) {
    this.id = id;
    this.owner = owner;
    this.creationTime = System.currentTimeMillis();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public Object getAttribute(Object key) {
    return getAttribute(key, null);
  }

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

  public RtspSessionAttributeMap getAttributeMap() {
    return attributes;
  }

}
