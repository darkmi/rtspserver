package com.darkmi.apm.request;

public class ExposeContentReq {

	private String providerID;
	private String assetID;
	private String volumeName;
	private String protocol;
	private String transferBitRate;

	public ExposeContentReq() {
		super();
	}

	public ExposeContentReq(String providerID, String assetID, String volumeName, String protocol,
			String transferBitRate) {
		super();
		this.providerID = providerID;
		this.assetID = assetID;
		this.volumeName = volumeName;
		this.protocol = protocol;
		this.transferBitRate = transferBitRate;
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

	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getTransferBitRate() {
		return transferBitRate;
	}

	public void setTransferBitRate(String transferBitRate) {
		this.transferBitRate = transferBitRate;
	}

	@Override
	public String toString() {
		return "ExposeContentReq [providerID=" + providerID + ", assetID=" + assetID + ", volumeName=" + volumeName
				+ ", protocol=" + protocol + ", transferBitRate=" + transferBitRate + "]";
	}

}
