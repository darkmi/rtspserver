package com.darkmi.apm.request;

public class CancelTransferReq {
	private String providerID;
	private String assetID;
	private String volumeName;
	private String reasonCode;

	public CancelTransferReq() {
		super();

	}

	public CancelTransferReq(String providerID, String assetID, String reasonCode, String volumeName) {
		super();
		this.providerID = providerID;
		this.assetID = assetID;
		this.reasonCode = reasonCode;
		this.volumeName = volumeName;
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

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	@Override
	public String toString() {
		return "CancelTransferReq [providerID=" + providerID + ", assetID=" + assetID + ", reasonCode=" + reasonCode
				+ ", volumeName=" + volumeName + "]";
	}

}
