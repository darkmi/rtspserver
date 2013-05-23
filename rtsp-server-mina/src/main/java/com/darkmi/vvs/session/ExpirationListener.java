package com.darkmi.vvs.session;

/**
 * A listener for expired object events.
 */
public interface ExpirationListener<E> {
    void expired(E expiredObject);
}
