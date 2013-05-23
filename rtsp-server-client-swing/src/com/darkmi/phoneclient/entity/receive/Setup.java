package com.darkmi.phoneclient.entity.receive;

import com.darkmi.phoneclient.tool.MessageType;


/**
 * ivr的setup返回实体
 * @author Administrator
 *
 */
public class Setup extends ResponseEntityBase{

	public Setup(MessageType msgType) {
		super.msgType = msgType;
	}
	
	private ResponseHeader rh;
	private String operationCode;
	private int scale;
	private int channelID;
	private int playMode;
	private int loopMode;
	private int playTimes;
	private int offeringID;
	private int nptBegin;
	private int nptTotal;
	private int count;
	private int assetId;
	public ResponseHeader getRh() {
		return rh;
	}
	public void setRh(ResponseHeader rh) {
		this.rh = rh;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public int getChannelID() {
		return channelID;
	}
	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}
	public int getPlayMode() {
		return playMode;
	}
	public void setPlayMode(int playMode) {
		this.playMode = playMode;
	}
	public int getLoopMode() {
		return loopMode;
	}
	public void setLoopMode(int loopMode) {
		this.loopMode = loopMode;
	}
	public int getPlayTimes() {
		return playTimes;
	}
	public void setPlayTimes(int playTimes) {
		this.playTimes = playTimes;
	}
	public int getOfferingID() {
		return offeringID;
	}
	public void setOfferingID(int offeringID) {
		this.offeringID = offeringID;
	}
	public int getNptBegin() {
		return nptBegin;
	}
	public void setNptBegin(int nptBegin) {
		this.nptBegin = nptBegin;
	}
	public int getNptTotal() {
		return nptTotal;
	}
	public void setNptTotal(int nptTotal) {
		this.nptTotal = nptTotal;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

}
