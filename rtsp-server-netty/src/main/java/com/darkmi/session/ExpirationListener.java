package com.darkmi.session;

/**
 * A listener for expired object events.
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 * TODO Make this a inner interface of ExpiringMap
 */
public interface ExpirationListener<E> {
    void expired(E expiredObject);
}
