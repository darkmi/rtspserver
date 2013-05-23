package com.darkmi.vvs.rs.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransferStatusDTO {
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if (status == null) {
			this.status = "";
		} else {
			this.status = status;
		}
	}

}
