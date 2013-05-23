package com.darkmi.vvs.rs.entity;

import java.util.Date;
import cn.com.supertv.entity.MidEntity;
import com.google.code.morphia.annotations.Entity;
@Entity(value = "vvs_transfer_status", noClassnameStored = true)
public class TransferStatus   extends MidEntity {
	private String providerID;
	private String assetID;
	private String state;
	private Integer reasonCode;
	private Integer percentComplete;
	private Integer contentSize;
	private Integer supportFileSize;
	private Integer md5Checksum;
	private Date md5DateTime;
	private Integer bitrate;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}
	public Integer getPercentComplete() {
		return percentComplete;
	}
	public void setPercentComplete(Integer percentComplete) {
		this.percentComplete = percentComplete;
	}
	public Integer getContentSize() {
		return contentSize;
	}
	public void setContentSize(Integer contentSize) {
		this.contentSize = contentSize;
	}
	public Integer getSupportFileSize() {
		return supportFileSize;
	}
	public void setSupportFileSize(Integer supportFileSize) {
		this.supportFileSize = supportFileSize;
	}
	public Integer getMd5Checksum() {
		return md5Checksum;
	}
	public void setMd5Checksum(Integer md5Checksum) {
		this.md5Checksum = md5Checksum;
	}
	public Date getMd5DateTime() {
		return md5DateTime;
	}
	public void setMd5DateTime(Date md5DateTime) {
		this.md5DateTime = md5DateTime;
	}
	public Integer getBitrate() {
		return bitrate;
	}
	public void setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
	}
	@Override
	public String toString() {
		return "TransferStatus [providerID=" + providerID + ", state=" + state  + ", assetID=" + assetID  + ", reasonCode=" + reasonCode  + 
			", percentComplete=" + percentComplete  + ", contentSize=" + contentSize  + ", supportFileSize=" + supportFileSize  
			+ ", md5DateTime=" + md5DateTime +", md5Checksum=" + md5Checksum + ", bitrate=" + bitrate + "]";
	}

}
