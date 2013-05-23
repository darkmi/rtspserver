package com.darkmi.vvs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darkmi.vvs.dao.TransferStatusDao;
import com.darkmi.vvs.rs.entity.TransferStatus;


/**
 * Description:tstv相关管理类 
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2012-1-4 下午05:49:31 yangzz created
 */
@Component
public class TransferStatusManager {
	private TransferStatusDao transferStatusDao;

	@Autowired
	public void setTransferStatusDao(TransferStatusDao transferStatusDao) {
		this.transferStatusDao = transferStatusDao;
	}

	public void requestStart() {
		transferStatusDao.getDatastore().getDB().requestStart();
	}

	public void requestDone() {
		transferStatusDao.getDatastore().getDB().requestDone();
	}

	public String getAssetStatus(String providerID, String assetID, String volumeName) {
		List<TransferStatus> transferStatusList = transferStatusDao.createQuery().field("providerID").equal(providerID).field("assetID").equal(assetID).field("volumeName").equal(volumeName).asList();
		if(transferStatusList==null||transferStatusList.size()==0){
			return null;
		}else{
			if(transferStatusList.size()>1){
				return "dup";
			}else{
				TransferStatus transferStatus= transferStatusList.get(0);
				return transferStatus.getState();
			}
			
		}
	}
}
