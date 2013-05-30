package com.darkmi.server.session;

import java.util.Set;

/**
 * 提供了在多个请求之间共享数据的机制.
 */
public interface RtspSession {

	/**
	 * 获得session标识符.
	 */
	String getId();

	/**
	 * 返回该session中用户自定义attribute的value.
	 * 
	 * @param key the key of the attribute
	 * @return <tt>null</tt> if there is no attribute with the specified key
	 */
	Object getAttribute(Object key);

	/** 
	 * 返回指定key的attribute的value.如果没有相应的attribute,则返回默认值,
	 * 并将该默认值与指定key关联.该方法与如下操作等效(except that the 
	 * operation is performed atomically):
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
	 * 设置用户自定义attribute.
	 *
	 * @param key   the key of the attribute
	 * @param value the value of the attribute
	 * @return The old value of the attribute.  <tt>null</tt> if it is new.
	 */
	Object setAttribute(Object key, Object value);

	/**
	 * 将指定key的attribute的value设置为{@link Boolean#TRUE}.当你想标记
	 * 一个attribute时,该操作很有用.
	 *
	 * @param key the key of the attribute
	 * @return The old value of the attribute.  <tt>null</tt> if it is new.
	 */
	Object setAttribute(Object key);

	/** 
	 * 如果用户指定key的attribute尚未赋值,则将其设置为指定的值.该方法与如下操作
	 * 等效(except that the operation is performed atomically):
	 *  
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
	 * 如果指定key的attribute尚未赋值,则将其赋值为{@link Boolean#TRUE}.
	 * 当你想标记一个attribute时,该操作很有用.该方法与如下操作等效(except
	 *  that the operation is performed atomically):
	 * 
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
	 *  移除指定key的attribute.
	 *
	 * @return The old value of the attribute.  <tt>null</tt> if not found.
	 */
	Object removeAttribute(Object key);

	/**
	 * 如果指定key的attribute的value与指定value相等,则将其移除.该方法与如下操作
	 * 等效(except that the operation is performed atomically):
	 * 
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
	 * 如果指定key的attribute的value与指定oldValue相等,则将其更新为newValue.该方法
	 * 与如下操作等效(except that the operation is performed atomically):
	 * 
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
	 * 如果包含指定<tt>key</tt>的attribute返回<tt>true</tt>.
	 */
	boolean containsAttribute(Object key);

	/**
	 * 返回所有用户自定义attribute的key.
	 */
	Set<Object> getAttributeKeys();

	/**
	 * @return session的创建时间(ms).
	 */
	long getCreationTime();
	
    /**
     * 销毁session,相关资源一起被销毁.
     * 销毁之后,即使客户端使用同一标识符,也不会再获取到该<code>session</code>.
     */
    public void destroy();
}
