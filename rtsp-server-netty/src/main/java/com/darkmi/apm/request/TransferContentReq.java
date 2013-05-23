package com.darkmi.apm.request;

/**
 * @Description
 * @author slaton.wu@gmail.com
 * @date 2012-6-13上午10:27:50
 * 
 */
public class TransferContentReq {
	private String providerID;
	private String assetID;
	private String captureStart;
	private String captureEnd;
	private String transferBitRate;
	private String sourceURL;
	private String sourceIP;
	private String sourceURL1;
	private String sourceIP1;
	private String userName;
	private String password;
	private String volumeName;
	private String responseURL;

	public TransferContentReq(String providerID, String assetID, String captureStart, String captureEnd,
			String transferBitRate, String sourceURL, String sourceIP, String sourceURL1, String sourceIP1,
			String userName, String password, String volumeName, String responseURL) {
		this.providerID = providerID;
		this.assetID = assetID;
		this.captureStart = captureStart;
		this.captureEnd = captureEnd;
		this.transferBitRate = transferBitRate;
		this.sourceURL = sourceURL;
		this.sourceIP = sourceIP;
		this.sourceURL1 = sourceURL1;
		this.sourceIP1 = sourceIP1;
		this.userName = userName;
		this.password = password;
		this.volumeName = volumeName;
		this.responseURL = responseURL;
	}

	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public String getAssetID() {
		return assetID;
	}

	public void setAssetID(String assetID) {
		this.assetID = assetID;
	}

	public String getCaptureStart() {
		return captureStart;
	}

	public void setCaptureStart(String captureStart) {
		this.captureStart = captureStart;
	}

	public String getCaptureEnd() {
		return captureEnd;
	}

	public void setCaptureEnd(String captureEnd) {
		this.captureEnd = captureEnd;
	}

	public String getTransferBitRate() {
		return transferBitRate;
	}

	public void setTransferBitRate(String transferBitRate) {
		this.transferBitRate = transferBitRate;
	}

	public String getSourceURL() {
		return sourceURL;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public String getSourceIP() {
		return sourceIP;
	}

	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}

	public String getSourceURL1() {
		return sourceURL1;
	}

	public void setSourceURL1(String sourceURL1) {
		this.sourceURL1 = sourceURL1;
	}

	public String getSourceIP1() {
		return sourceIP1;
	}

	public void setSourceIP1(String sourceIP1) {
		this.sourceIP1 = sourceIP1;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	public String getResponseURL() {
		return responseURL;
	}

	public void setResponseURL(String responseURL) {
		this.responseURL = responseURL;
	}

	@Override
	public String toString() {
		return "TransferContent [providerID=" + providerID + ", assetID=" + assetID + ", captureStart=" + captureStart
				+ ", captureEnd=" + captureEnd + ", transferBitRate=" + transferBitRate + ", sourceURL=" + sourceURL
				+ ", sourceIP=" + sourceIP + ", sourceURL1=" + sourceURL1 + ", sourceIP1=" + sourceIP1 + ", userName="
				+ userName + ", password=" + password + ", volumeName=" + volumeName + ", responseURL=" + responseURL
				+ "]";
	}

}
