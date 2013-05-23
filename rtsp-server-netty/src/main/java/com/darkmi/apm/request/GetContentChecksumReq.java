package com.darkmi.apm.request;

public class GetContentChecksumReq {
	private String providerID;
	private String assetID;
	private String volumeName;
	private String responseURL;

	public GetContentChecksumReq() {
		super();
	}

	public GetContentChecksumReq(String providerID, String assetID, String volumeName, String responseURL) {
		super();
		this.providerID = providerID;
		this.assetID = assetID;
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
		return "GetContentChecksumReq [providerID=" + providerID + ", assetID=" + assetID + ", volumeName="
				+ volumeName + ", responseURL=" + responseURL + "]";
	}

}
