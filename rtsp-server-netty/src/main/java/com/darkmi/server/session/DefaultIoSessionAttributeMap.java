package com.darkmi.server.session;

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

  @Override
  public Object getAttribute(Object key, Object defaultValue) {
    if (key == null) {
      throw new IllegalArgumentException("key");
    }

    Object answer = attributes.get(key);
    if (answer == null) {
      return defaultValue;
    }

    return answer;
  }

  @Override
  public Object setAttribute(Object key, Object value) {
    if (key == null) {
      throw new IllegalArgumentException("key");
    }

    if (value == null) {
      return attributes.remove(key);
    }

    return attributes.put(key, value);
  }

  @Override
  public Object setAttributeIfAbsent(Object key, Object value) {
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

  @Override
  public Object removeAttribute(Object key) {
    if (key == null) {
      throw new IllegalArgumentException("key");
    }

    return attributes.remove(key);
  }

  @Override
  public boolean removeAttribute(Object key, Object value) {
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

  @Override
  public boolean replaceAttribute(Object key, Object oldValue, Object newValue) {
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

  @Override
  public boolean containsAttribute(Object key) {
    return attributes.containsKey(key);
  }

  @Override
  public Set<Object> getAttributeKeys() {
    synchronized (attributes) {
      return new HashSet<Object>(attributes.keySet());
    }
  }

  @Override
  public void dispose() throws Exception {
    // Do nothing
  }
}
