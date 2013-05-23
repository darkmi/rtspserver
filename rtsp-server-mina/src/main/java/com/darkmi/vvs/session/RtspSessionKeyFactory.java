package com.darkmi.vvs.session;

/**
 * 一个创建session key的factory.
 * <code>SessionKeyFactory</code>需要满足以下要求:
 * <ul>
 *   <li>避免产生相同的两个key,比如 <i>k(1), k(2)</i>,要避免 <i>k(1) == k(2)</i></li>
 *   <li>避免产生有规律的key</li>
 * </ul>
 */
public interface RtspSessionKeyFactory {

    /**
     * 返回一个session key字符串.
     *
     * @return  The session key
     */
    public String createSessionKey();

}
