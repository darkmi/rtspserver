package com.darkmi.phoneclient.entity.send;

import java.nio.ByteBuffer;

import myUtil.ConversionUtil;

public class RequestPlay {

	public RequestPlay() {
	}

	private int offeringID;
	private int isBookmark;

	public int getOfferingID() {
		return offeringID;
	}

	public void setOfferingID(int offeringID) {
		this.offeringID = offeringID;
	}

	public int getIsBookmark() {
		return isBookmark;
	}

	public void setIsBookmark(int isBookmark) {
		this.isBookmark = isBookmark;
	}

	public byte[] toBytes() {
		ByteBuffer bb = ByteBuffer.allocate(50);
		bb.put(ConversionUtil.int2bytes(offeringID, 4));
		bb.put(ConversionUtil.int2bytes(isBookmark, 1));
		bb.flip();
		byte[] b = new byte[bb.limit()];
		bb.get(b);
		return b;
	}

}
