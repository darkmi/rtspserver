package com.darkmi.vvs.rs.entity;

import java.util.Date;
import cn.com.supertv.entity.MidEntity;
import com.google.code.morphia.annotations.Entity;
@Entity(value = "vvs_content_info", noClassnameStored = true)
public class ContentInfo   extends MidEntity {
	private String providerID;
	private String assetID;
	private String volumeName;
	private Integer reasonCode;
	private Integer percentComplete;
	private Integer contentSize;
	private Integer supportFileSize;
	private String md5Checksum;
	private Date md5DateTime;
	private Date createDate;
	private ContentStateEnum contentState;
	private Date captureStart;
	private Date captureEnd;
	private Integer transferBitRate;
	private String sourceURL;
	private String sourceIP;
	private String sourceURL1;
	private String sourceIP1;
	private String username;
	private String password;
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
	public String getVolumeName() {
		return volumeName;
	}
	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
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
	public String getMd5Checksum() {
		return md5Checksum;
	}
	public void setMd5Checksum(String md5Checksum) {
		this.md5Checksum = md5Checksum;
	}
	public Date getMd5DateTime() {
		return md5DateTime;
	}
	public void setMd5DateTime(Date md5DateTime) {
		this.md5DateTime = md5DateTime;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public ContentStateEnum getContentState() {
		return contentState;
	}
	public void setContentState(ContentStateEnum contentState) {
		this.contentState = contentState;
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
	public String getResponseURL() {
		return responseURL;
	}
	public void setResponseURL(String responseURL) {
		this.responseURL = responseURL;
	}
	@Override
	public String toString() {
		return "TransferStatus [providerID=" + providerID + ", volumeName=" + volumeName  + ", assetID=" + assetID  + ", reasonCode=" + reasonCode  + 
			", percentComplete=" + percentComplete  + ", contentSize=" + contentSize  + ", supportFileSize=" + supportFileSize  
			+ ", md5DateTime=" + md5DateTime +", md5Checksum=" + md5Checksum + ", contentState=" + contentState + "]";
	}

}
