package com.darkmi.server.util;

/**
 * Something interested in receiving notifications when permits issued by a
 * <code>TimedPermitIssuer</code> exipre
 *
 *
 */
public interface PermitExpirationListener {

    /**
     * Invoked when the permit associated with the specified object expires
     *
     * @param o The object for which an associated permit has expired
     */
    public void permitExpired(Object o);

}
