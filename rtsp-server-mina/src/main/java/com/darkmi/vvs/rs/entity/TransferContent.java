package com.darkmi.vvs.rs.entity;

import java.util.Date;

import cn.com.supertv.entity.MidEntity;

import com.google.code.morphia.annotations.Entity;
@Entity(value = "vvs_transfer_content", noClassnameStored = true)
public class TransferContent  extends MidEntity {
	private String providerID;
	private String assetID;
	private Date captureStart;
	private Date captureEnd;
	private Integer transferBitRate;
	private String sourceURL;
	private String sourceIP;
	private String sourceURL1;
	private String sourceIP1;
	private String username;
	private String password;
	private String volumeName;
	private String responseURL;
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
	public Date getCaptureStart() {
		return captureStart;
	}
	public void setCaptureStart(Date captureStart) {
		this.captureStart = captureStart;
	}
	public Date getCaptureEnd() {
		return captureEnd;
	}
	public void setCaptureEnd(Date captureEnd) {
		this.captureEnd = captureEnd;
	}
	public Integer getTransferBitRate() {
		return transferBitRate;
	}
	public void setTransferBitRate(Integer transferBitRate) {
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
		return "TransferStatus [providerID=" + providerID + ", transferBitRate=" + transferBitRate  + ", assetID=" + assetID  + ", sourceURL=" + sourceURL  + 
			", sourceURL1=" + sourceURL1 +", sourceIP=" + sourceIP   + ", sourceIP1=" + sourceIP1  + ", username=" + username  
			+ ", password=" + password +", volumeName=" + volumeName + ", responseURL=" + responseURL + "]";
	}
}
