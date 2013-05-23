package com.darkmi.vvs.session;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 
 */
public class SimpleRandomKeyFactory implements RtspSessionKeyFactory {

	private static final int HEADER_COUNT = 8;
	private static final int FOOTER_COUNT = 10;
	private static final String DELIMITER = "-";

	@Override
	public synchronized String createSessionKey() {
		String header = RandomStringUtils.randomNumeric(HEADER_COUNT);
		String footer = RandomStringUtils.randomNumeric(FOOTER_COUNT);
		return header + DELIMITER + footer;
	}

	public static void main(String[] args) {
		SimpleRandomKeyFactory srf = new SimpleRandomKeyFactory();
		System.out.println(srf.createSessionKey());
	}

}
