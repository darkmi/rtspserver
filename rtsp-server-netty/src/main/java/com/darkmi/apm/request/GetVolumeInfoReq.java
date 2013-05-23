package com.darkmi.apm.request;

import org.apache.commons.lang.builder.ToStringBuilder;

public class GetVolumeInfoReq {

	private String volumeName;

	public GetVolumeInfoReq() {
		super();
	}

	public GetVolumeInfoReq(String volumeName) {
		super();
		this.volumeName = volumeName;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
