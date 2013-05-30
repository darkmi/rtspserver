package com.darkmi.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Npt {
	private double timeStart;
	private double timeEnd;
	private boolean live;

	private static final Pattern ntpPattern = Pattern.compile("npt=([^-]*)-([^-]*)");
	private static final Pattern ntpHHMMSSPattern = Pattern.compile("npt=([0-9]+)-([^-]*)");

	/**
	 * Construct a new Npt
	 */
	public Npt() {
		timeStart = 0.0;
		timeEnd = 0.0;
	}

	/**
	 * Construct a new Npt description parsing data from a String
	 * 
	 * @param ntpString the String containg the description
	 * @return a new Npt object or <code>null</code> if the description is not
	 *         valid
	 */
	public static Npt fromString(String ntpString) {
		Matcher m1 = ntpPattern.matcher(ntpString);
		Matcher m2 = ntpHHMMSSPattern.matcher(ntpString);
		double start = 0.0, end = 0.0;
		boolean live = false;

		if (m1.matches()) {
			String sStart = m1.group(1);
			String sEnd = m1.group(2);

			if ("now".equals(sStart)) {
				live = true;
			} else {
				live = false;
				try {
					start = Double.parseDouble(sStart);
					if (sEnd.length() != 0)
						end = Double.parseDouble(sEnd);
				} catch (NumberFormatException e) {
					// strings format is not valid
					return null;
				}
			}
		} else if (m2.matches()) {

		} else {
			// Format not valid 
		}

		Npt npt = new Npt();
		npt.setTimeStart(start);
		npt.setTimeEnd(end);
		npt.setLive(live);

		return npt;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("npt=");
		if (live)
			s.append("now-");
		else {
			if (timeStart == 0.0)
				s.append("0-");
			else {
				s.append(Double.toString(timeStart));
				s.append("-");
			}

			if (timeEnd != 0.0) {
				// If timeEnd is 0 we can omit it
				s.append(Double.toString(timeEnd));
			}
		}

		return s.toString();
	}

	/**
	 * @return Returns true if the timeing is <i>now</i> and so it represents a
	 *         live event.
	 */
	public boolean isLive() {
		return live;
	}

	/**
	 * @return Returns the timeEnd.
	 */
	public double getTimeEnd() {
		return timeEnd;
	}

	/**
	 * @param timeEnd The timeEnd to set.
	 */
	public void setTimeEnd(double timeEnd) {
		this.timeEnd = timeEnd;
	}

	/**
	 * @return Returns the timeStart.
	 */
	public double getTimeStart() {
		return timeStart;
	}

	/**
	 * @param timeStart The timeStart to set.
	 */
	public void setTimeStart(double timeStart) {
		this.timeStart = timeStart;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

}
