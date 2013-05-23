package com.darkmi.session;

import java.util.Set;

/**
 * 提供了在多个请求之间共享数据的机制.
 */
public interface RtspSession {

	/**
	 * 获得session标识符.
	 * 
	 * @return
	 */
	String getId();

	/**
	 * 返回该session中用户自定义attribute的value.
	 * 
	 * @param key the key of the6 attribute
	 * @return <tt>null</tt> if there is no attribute with the specified key
	 */
	Object getAttribute(Object key);

	/**
	 * Returns the value of user defined attribute associated with the
	 * specified key.  If there's no such attribute, the specified default
	 * value is associated with the specified key, and the default value is
	 * returned.  This method is same with the following code except that the
	 * operation is performed atomically.
	 * <pre>
	 * if (containsAttribute(key)) {
	 *     return getAttribute(key);
	 * } else {
	 *     setAttribute(key, defaultValue);
	 *     return defaultValue;
	 * }
	 * </pre>
	 */
	Object getAttribute(Object key, Object defaultValue);

	/**
	 * Sets a user-defined attribute.
	 *
	 * @param key   the key of the attribute
	 * @param value the value of the attribute
	 * @return The old value of the attribute.  <tt>null</tt> if it is new.
	 */
	Object setAttribute(Object key, Object value);

	/**
	 * Sets a user defined attribute without a value.  This is useful when
	 * you just want to put a 'mark' attribute.  Its value is set to
	 * {@link Boolean#TRUE}.
	 *
	 * @param key the key of the attribute
	 * @return The old value of the attribute.  <tt>null</tt> if it is new.
	 */
	Object setAttribute(Object key);

	/**
	 * Sets a user defined attribute if the attribute with the specified key
	 * is not set yet.  This method is same with the following code except
	 * that the operation is performed atomically.
	 * <pre>
	 * if (containsAttribute(key)) {
	 *     return getAttribute(key);
	 * } else {
	 *     return setAttribute(key, value);
	 * }
	 * </pre>
	 */
	Object setAttributeIfAbsent(Object key, Object value);

	/**
	 * Sets a user defined attribute without a value if the attribute with
	 * the specified key is not set yet.  This is useful when you just want to
	 * put a 'mark' attribute.  Its value is set to {@link Boolean#TRUE}.
	 * This method is same with the following code except that the operation
	 * is performed atomically.
	 * <pre>
	 * if (containsAttribute(key)) {
	 *     return getAttribute(key);  // might not always be Boolean.TRUE.
	 * } else {
	 *     return setAttribute(key);
	 * }
	 * </pre>
	 */
	Object setAttributeIfAbsent(Object key);

	/**
	 * Removes a user-defined attribute with the specified key.
	 *
	 * @return The old value of the attribute.  <tt>null</tt> if not found.
	 */
	Object removeAttribute(Object key);

	/**
	 * Removes a user defined attribute with the specified key if the current
	 * attribute value is equal to the specified value.  This method is same
	 * with the following code except that the operation is performed
	 * atomically.
	 * <pre>
	 * if (containsAttribute(key) && getAttribute(key).equals(value)) {
	 *     removeAttribute(key);
	 *     return true;
	 * } else {
	 *     return false;
	 * }
	 * </pre>
	 */
	boolean removeAttribute(Object key, Object value);

	/**
	 * Replaces a user defined attribute with the specified key if the
	 * value of the attribute is equals to the specified old value.
	 * This method is same with the following code except that the operation
	 * is performed atomically.
	 * <pre>
	 * if (containsAttribute(key) && getAttribute(key).equals(oldValue)) {
	 *     setAttribute(key, newValue);
	 *     return true;
	 * } else {
	 *     return false;
	 * }
	 * </pre>
	 */
	boolean replaceAttribute(Object key, Object oldValue, Object newValue);

	/**
	 * Returns <tt>true</tt> if this session contains the attribute with
	 * the specified <tt>key</tt>.
	 */
	boolean containsAttribute(Object key);

	/**
	 * Returns the set of keys of all user-defined attributes.
	 */
	Set<Object> getAttributeKeys();

	/**
	 * @return the session's creation time in milliseconds
	 */
	long getCreationTime();
}
