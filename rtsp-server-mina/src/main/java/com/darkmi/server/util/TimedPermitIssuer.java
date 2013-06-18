package com.darkmi.server.util;

/**
 * Description: Issues permits for accessing objects for a limited period of time.
 * Users may renew the lifetime of an issued permit by invoking its <code>renew</code>
 * method. If a permit issued by this issuer expires before it is renewed, all
 * <code>PermitExpirationListener</code>s associated with this issuer are notified of
 * the expiry.
 */
public interface TimedPermitIssuer {

	/**
	 * Closes this permit issuer
	 */
	public void close();

	/**
	 * Issues a new permit for a specified object
	 *
	 * @param o  The object for which a permit is required
	 * @return   The new permit
	 */
	public TimedPermit issuePermit(Object o);

	/**
	 * Adds a listener to be notified when any permit supplied by this issuer
	 * expires
	 *
	 * @param listener  The listener
	 */
	public void addPermitExpirationListener(PermitExpirationListener listener);

}
