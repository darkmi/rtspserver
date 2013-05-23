package com.darkmi.vvs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.darkmi.vvs.dao.ContentInfoDao;
import com.darkmi.vvs.rs.dto.ContentInfoDTO;
import com.darkmi.vvs.rs.dto.TransferContenDTO;
import com.darkmi.vvs.rs.dto.VolumeInfoDTO;
import com.darkmi.vvs.rs.entity.ContentInfo;
import com.google.common.collect.Lists;

/**
 * Description: 
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2012-6-5 下午03:04:04 saiwengang created
 */
@Component
public class TransferContentManager {
	private ContentInfoDao contentInfoDao;

	@Autowired
	public void setContentInfoDao(ContentInfoDao contentInfoDao) {
		this.contentInfoDao = contentInfoDao;
	}

	public void requestStart() {
		contentInfoDao.getDatastore().getDB().requestStart();
	}

	public void requestDone() {
		contentInfoDao.getDatastore().getDB().requestDone();
	}

	public void saveTransferContent(TransferContenDTO transferContentDTO) {
		ContentInfo contentInfo = new ContentInfo();
		contentInfo.setAssetID(transferContentDTO.getAssetID());
		contentInfo.setCaptureEnd(transferContentDTO.getCaptureEnd());
		contentInfo.setCaptureStart(transferContentDTO.getCaptureStart());
		contentInfo.setPassword(transferContentDTO.getPassword());
		contentInfo.setProviderID(transferContentDTO.getProviderID());
		contentInfo.setResponseURL(transferContentDTO.getResponseURL());
		contentInfo.setSourceIP(transferContentDTO.getSourceIP());
		contentInfo.setSourceIP1(transferContentDTO.getSourceIP1());
		contentInfo.setSourceURL(transferContentDTO.getSourceURL());
		contentInfo.setSourceURL1(transferContentDTO.getSourceURL1());
		contentInfo.setTransferBitRate(transferContentDTO.getTransferBitRate());
		contentInfo.setUsername(transferContentDTO.getUsername());
		contentInfo.setVolumeName(transferContentDTO.getVolumeName());
		contentInfoDao.save(contentInfo);
		
	}

	public void cancelTransfer(String providerID, String assetID, String volumeName, Integer reasonCode) {
		ContentInfo contengInfo = contentInfoDao.findOne(contentInfoDao.createQuery()
											.field("providerID").equal(providerID)
											.field("assetID").equal(assetID)
											.field("volumeName").equal(volumeName));
		if(contengInfo!=null)
			contengInfo.setReasonCode(reasonCode);
		contentInfoDao.save(contengInfo);
	}

	public void deleteContent(String providerID, String assetID, String volumeName, Integer reasonCode) {
		ContentInfo contengInfo = contentInfoDao.findOne(contentInfoDao.createQuery()
												.field("providerID").equal(providerID)
												.field("assetID").equal(assetID)
												.field("volumeName").equal(volumeName));
		if(contengInfo!=null)
		contengInfo.setReasonCode(reasonCode);
		contentInfoDao.save(contengInfo);
	}

	public List<ContentInfoDTO> getContentInfo(String providerID, String assetID, String volumeName) {
		List<ContentInfo> contentInfoList = contentInfoDao.createQuery().field("providerID").equal(providerID)
											.field("assetID").equal(assetID)
											.field("volumeName").equal(volumeName).asList();
		 List<ContentInfoDTO> contentInfoDtoList = Lists.newArrayList();
		 for(ContentInfo contentInfo:contentInfoList){
			 ContentInfoDTO contentInfoDTO = new ContentInfoDTO();
			 contentInfoDTO.setAssetID(contentInfo.getAssetID());
			 contentInfoDTO.setContentSize(contentInfo.getContentSize());
			 contentInfoDTO.setContentState(contentInfo.getContentState());
			 contentInfoDTO.setCreateDate(contentInfo.getCreateDate());
			 contentInfoDTO.setMd5Checksum(contentInfo.getMd5Checksum());
			 contentInfoDTO.setMd5DateTime(contentInfo.getMd5DateTime());
			 contentInfoDTO.setPercentComplete(contentInfo.getPercentComplete());
			 contentInfoDTO.setProviderID(contentInfo.getProviderID());
			 contentInfoDTO.setReasonCode(contentInfo.getReasonCode());
			 contentInfoDTO.setSupportFileSize(contentInfo.getSupportFileSize());
			 contentInfoDTO.setVolumeName(contentInfo.getVolumeName());
			 contentInfoDtoList.add(contentInfoDTO);
		 }
		return contentInfoDtoList;
	}

	public VolumeInfoDTO getVolumeInfo(String volumeName) {
		List<ContentInfo> contentInfoList = contentInfoDao.createQuery()
											.field("volumeName").equal(volumeName).asList();
		VolumeInfoDTO volumeInfoDTO = new VolumeInfoDTO();
		volumeInfoDTO.setFreeSize(23);
		volumeInfoDTO.setState(2);
		volumeInfoDTO.setVolumeName(volumeName);
		volumeInfoDTO.setVolumeSize(50);
		if(contentInfoList!=null&&contentInfoList.size()>0){
		}
		return volumeInfoDTO;
	}
	public String getAssetStatus(String providerID, String assetID, String volumeName) {
		List<ContentInfo> transferStatusList = contentInfoDao.createQuery().field("providerID").equal(providerID).field("assetID").equal(assetID).field("volumeName").equal(volumeName).asList();
		if(transferStatusList==null||transferStatusList.size()==0){
			return null;
		}else{
			if(transferStatusList.size()>1){
				return "dup";
			}else{
				ContentInfo contentInfo= transferStatusList.get(0);
				return contentInfo.getContentState().getLabel();
			}
			
		}
	}
}
