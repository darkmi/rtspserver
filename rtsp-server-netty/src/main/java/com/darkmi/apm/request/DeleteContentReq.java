package com.darkmi.apm.request;

public class DeleteContentReq {
	private String providerID;
	private String assetID;
	private String volumeName;
	private String reasonCode;

	public DeleteContentReq() {
		super();
	}

	public DeleteContentReq(String providerID, String assetID, String volumeName, String reasonCode) {
		super();
		this.providerID = providerID;
		this.assetID = assetID;
		this.volumeName = volumeName;
		this.reasonCode = reasonCode;
	}

	public String getAssetID() {
		return assetID;
	}

	public void setAssetID(String assetID) {
		this.assetID = assetID;
	}

	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
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
		return "DeleteContent [assetID=" + assetID + ", providerID=" + providerID + ", reasonCode=" + reasonCode
				+ ", volumeName=" + volumeName + "]";
	}
}
