package com.darkmi.apm.request;


public class GetTransferStatusReq {
	private String providerID;
	private String assetID;
	private String volumeName;
	
	
	public GetTransferStatusReq() {
		super();
	}

	public GetTransferStatusReq(String providerID, String assetID, String volumeName) {
		super();
		this.providerID = providerID;
		this.assetID = assetID;
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
		return "GetTransferStatus [providerID=" + providerID + ", assetID=" + assetID + ", volumeName=" + volumeName
				+ "]";
	}

}
