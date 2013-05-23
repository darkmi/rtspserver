package com.darkmi.vvs.rs.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.darkmi.vvs.rs.entity.ContentStateEnum;

@XmlRootElement
public class ContentInfoDTO{
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
	
	@Override
	public String toString() {
		return "TransferStatus [providerID=" + providerID + ", volumeName=" + volumeName  + ", assetID=" + assetID  + ", reasonCode=" + reasonCode  + 
			", percentComplete=" + percentComplete  + ", contentSize=" + contentSize  + ", supportFileSize=" + supportFileSize  
			+ ", md5DateTime=" + md5DateTime +", md5Checksum=" + md5Checksum + ", contentState=" + contentState + "]";
	}

}
