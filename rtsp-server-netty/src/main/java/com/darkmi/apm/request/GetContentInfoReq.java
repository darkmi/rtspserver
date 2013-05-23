package com.darkmi.apm.request;

public class GetContentInfoReq {
	private String assetID;
	private String providerID;
	private String volumeName;

	public GetContentInfoReq() {
		super();
	}

	public GetContentInfoReq(String assetID, String providerID, String volumeName) {
		super();
		this.assetID = assetID;
		this.providerID = providerID;
		this.volumeName = volumeName;
	}

	public String getAssetID() {
		return assetID;
	}

	public String getProviderID() {
		return providerID;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setAssetID(String assetID) {
		this.assetID = assetID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	@Override
	public String toString() {
		return "GetContentInfoReq [assetID=" + assetID + ", providerID=" + providerID + ", volumeName=" + volumeName
				+ "]";
	}

}
