package com.darkmi.apm.response;

public class TranferStatusResp {
	private String assetID;
	private String contentSize;
	private String md5Checksum;
	private String md5DateTime;
	private Integer percentComplete;
	private String providerID;
	private String state;
	private String supportFileSize;
	private String volumeName;

	public String getAssetID() {
		return assetID;
	}

	public String getContentSize() {
		return contentSize;
	}

	public String getMd5Checksum() {
		return md5Checksum;
	}

	public String getMd5DateTime() {
		return md5DateTime;
	}

	public Integer getPercentComplete() {
		return percentComplete;
	}

	public String getProviderID() {
		return providerID;
	}

	public String getState() {
		return state;
	}

	public String getSupportFileSize() {
		return supportFileSize;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setAssetID(String assetID) {
		this.assetID = assetID;
	}

	public void setContentSize(String contentSize) {
		this.contentSize = contentSize;
	}

	public void setMd5Checksum(String md5Checksum) {
		this.md5Checksum = md5Checksum;
	}

	public void setMd5DateTime(String md5DateTime) {
		this.md5DateTime = md5DateTime;
	}

	public void setPercentComplete(Integer percentComplete) {
		this.percentComplete = percentComplete;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setSupportFileSize(String supportFileSize) {
		this.supportFileSize = supportFileSize;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

}
