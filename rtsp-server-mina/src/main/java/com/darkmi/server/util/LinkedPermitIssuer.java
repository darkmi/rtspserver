package com.darkmi.server.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: A <code>TimedPermitIssuer</code> which stores all issued permits in a linked
 * list.
 * As a permit is renewed, its lifetime is extended and it is simply moved to the
 * back of the list (As <code>LinkedPermitIssuer</code> uses a fixed lifetime for
 * all permits and renewals).<br/>
 * Each permit issued by this issuer has direct access to its place in the list -
 * allowing constant time renewals.
 * 
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2011-3-15 上午11:13:26 MiXiaohui created
 */
public class LinkedPermitIssuer implements TimedPermitIssuer {

	private static final Logger LOG = LoggerFactory.getLogger(LinkedPermitIssuer.class);

	/**
	 * The head of the permit list.
	 * This entry is the next due to expire
	 */
	private PermitEntry head;

	/**
	 * The tail of the permit list
	 */
	private PermitEntry tail;

	/**
	 * The lifetime given to new permits, and permit renewals
	 */
	private long lifetime;

	private Object lock = new Object();

	private List<PermitExpirationListener> listeners = Collections.synchronizedList(new ArrayList<PermitExpirationListener>());

	private boolean isClosed;

	/**
	 * Creates a <code>LinkedPermitIssuer</code> with a specified lifetime given to
	 * new permits. When a permit issued by this issuer is renewed, its expiry time
	 * is renewed to the current time plus this lifetime
	 *
	 * @param lifetime  The lifetime to be used for new permits, and permit renewals
	 */
	public LinkedPermitIssuer(long lifetime) {
		if (lifetime <= 0) {
			throw new IllegalArgumentException("lifetime must be >0");
		}
		this.lifetime = lifetime;
		//开始计时
		new Thread(new ExpiryNotifier()).start();
	}

	/**
	 * Issues a new <code>TimedPermit</code> for the target object.
	 * Unless <code>renew</code>ed, the permit expires after this
	 * issuers imposed lifetime. Upon renewal, the permit becomes valid
	 * for this issuers configured lifetime from the time of the renewal.
	 *
	 * @param o The target object
	 */
	public TimedPermit issuePermit(Object o) {
		PermitEntry permit;
		synchronized (lock) {
			permit = new PermitEntry(o);
			if (isEmpty()) {
				head = tail = permit;
				lock.notify(); // notify when move from empty to non empty
			} else {
				tail.entryAfter = permit;
				permit.entryBefore = tail;
				tail = permit;
			}
		}
		return permit;
	}

	/**
	 * Adds a <code>PermitExpirationListener</code> to this issuer
	 *
	 * @param listener the listener
	 */
	public void addPermitExpirationListener(PermitExpirationListener listener) {
		listeners.add(listener);
	}

	/**
	 * Closes this issuer
	 */
	public void close() {
		synchronized (lock) {
			isClosed = true;
			lock.notify();
			LOG.debug("Marked as closed");
		}
	}

	/**
	 * Determines whether there are any outstanding permits
	 * to be processed
	 *
	 * @return  <code>true</code> if there are no outstanding
	 *          permits
	 */
	private boolean isEmpty() {
		return head == null;
	}

	/**
	 * Moves a <code>PermitEntry</code> to the back of the list.
	 * An entry is moved to the back upon renewal. If we move the current head
	 * to the back, we notify to allow the expiry time of the next element
	 * to be observed
	 *
	 * @param entry  The entry to move
	 */
	private void moveToBack(PermitEntry entry) {
		boolean movedHead = entry == head;
		if (entry != tail) { // nothing to move / no need to notify if already at back
			PermitEntry previous = entry.entryBefore;
			PermitEntry after = entry.entryAfter;

			tail.entryAfter = entry;
			entry.entryBefore = tail;
			entry.entryAfter = null;
			tail = entry;

			after.entryBefore = previous;
			if (!movedHead) {
				previous.entryAfter = after;
			} else {
				head = after;
				lock.notify();
			}
		}
	}

	/**
	 * Removes the head entry (without notifications)
	 */
	private void removeHead() {
		head = head.entryAfter;
		if (head != null) {
			head.entryBefore = null;
		} else {
			LOG.debug("Permit list empty following removal");
		}
	}

	/**
	 * Notifies all listeners of the expiry of a specified target object
	 *
	 * @param target  The expired target object
	 */
	private void notifyExpiry(Object target) {
		synchronized (listeners) {
			for (PermitExpirationListener listener : listeners) {
				listener.permitExpired(target);
			}
		}
	}

	/**
	 * An entry in a linked list of permits.
	 *
	 *
	 */
	private class PermitEntry implements TimedPermit {

		private PermitEntry entryBefore;

		private PermitEntry entryAfter;

		private long expiryTime;

		private Object o;

		private boolean isCancelled;

		PermitEntry(Object o) {
			this.o = o;
			extendLifetime();
		}

		/**
		 * Renews this permit. If we are cancelled, no action is taken.
		 * Otherwise, our lifetime is extended, and we move to the back of the
		 * list
		 */
		public void renew() {
			synchronized (lock) {
				if (!isCancelled) {
					extendLifetime();
					moveToBack(this);
					if (LOG.isDebugEnabled()) {
						LOG.debug("Entry has been renewed. New expiry time: " + expiryTime);
					}
				}
			}
		}

		/**
		 * @return  This permits target object
		 */
		Object getTarget() {
			return o;
		}

		/**
		 * @return the time remaining, in ms until this permit
		 * expires
		 */
		long timeToExpiry() {
			return expiryTime - System.currentTimeMillis();
		}

		/**
		 * @return  <code>true</code> if this permit has been cancelled and should be
		 *          removed
		 */
		boolean isCancelled() {
			return isCancelled;
		}

		/**
		 * Cancels this permit.
		 * If we are either expired or already cancelled, no action is taken.
		 * Otherwise, we mark ourself as cancelled.
		 * If we are at the head of the permit list, we notify to allow
		 * the notification thread to take any required action.
		 */
		public boolean cancel() {
			synchronized (lock) {
				if (isCancelled) {
					LOG.debug("Ignoring cancel request");
					return false;
				}
				isCancelled = true;
				LOG.debug("Entry has been successfully cancelled");
				if (this == head) {
					LOG.debug("Head entry cancelled - notifying");
					lock.notify();
				}
				return true;
			}
		}

		/**
		 * Marks this permit as cancelled
		 */
		void markCancelled() {
			isCancelled = true;
		}

		/**
		 * Extends our lifetime
		 */
		private void extendLifetime() {
			expiryTime = System.currentTimeMillis() + lifetime;
		}

	}

	/**
	 * Services the head of the permit list, blocking until work is available.
	 * Cancelled and expired entries are removed (listeners are notified of all
	 * expired entries)
	 *
	 */
	private class ExpiryNotifier implements Runnable {

		public void run() {
			try {
				LOG.debug("ExpiryNotifier starting");
				while (processHeadEntry()) {
					continue;
				}
				LOG.debug("ExpiryNotifier closing");
			} catch (RuntimeException e) {
				LOG.error("Unexpected exception on expiry notifier", e);
			}

		}

		/**
		 * Waits for cancellation / expiration of the head entry.
		 * If the head entry is expired, listeners are notified.
		 *
		 * @return  <code>true</code> if the entry is processed,
		 *          <code>false</code> if we become closed while waiting for a result
		 */
		private boolean processHeadEntry() {
			PermitEntry toExpire = null;
			try {
				synchronized (lock) {
					while (!isClosed && isEmpty()) {
						lock.wait();
					}
					if (isClosed) {
						return false;
					} else {
						toExpire = processFirst();
					}
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if (toExpire != null) {
				notifyExpiry(toExpire.getTarget());
			}
			return true;
		}

		/**
		 * Examines the head of the list for cancellation or expiry.
		 * We block until either the entry is cancelled, expires, or we are
		 * closed
		 *
		 * @return  The previous head entry - if it has expired.
		 *          <code>null</code> if the entry was cancelled, or we were closed
		 */
		private PermitEntry processFirst() {
			PermitEntry entry = head;
			boolean expired = false;
			long timeToExpiry = entry.timeToExpiry();
			while (!isClosed && !entry.isCancelled() && timeToExpiry > 0) {
				try {
					if (LOG.isDebugEnabled()) {
						LOG.debug("Waiting for head entry to expire: " + timeToExpiry + "ms");
					}
					lock.wait(timeToExpiry);
				} catch (InterruptedException e) {
					throw new RuntimeException("Unexpected interrupt");
				}
				timeToExpiry = entry.timeToExpiry();
			}

			if (entry.isCancelled()) {
				LOG.debug("Head entry is cancelled. Removing");
				removeHead();
			} else if (!isClosed) {
				LOG.debug("Head entry has expired");
				entry.markCancelled();
				removeHead();
				expired = true;
			}
			return expired ? entry : null;
		}

	}

}
