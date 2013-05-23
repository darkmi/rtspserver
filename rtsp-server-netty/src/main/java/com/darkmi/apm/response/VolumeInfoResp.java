package com.darkmi.apm.response;

public class VolumeInfoResp {
	private String freeSize;
	private String state;
	private String volumeName;
	private String volumeSize;

	public String getFreeSize() {
		return freeSize;
	}

	public String getState() {
		return state;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public String getVolumeSize() {
		return volumeSize;
	}

	public void setFreeSize(String freeSize) {
		this.freeSize = freeSize;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	public void setVolumeSize(String volumeSize) {
		this.volumeSize = volumeSize;
	}
}
