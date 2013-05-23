package com.darkmi.vvs.rs.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VolumeInfoDTO  {
	private String volumeName;
	private Integer state;
	private Integer volumeSize;
	private Integer freeSize;
	public String getVolumeName() {
		return volumeName;
	}
	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getVolumeSize() {
		return volumeSize;
	}
	public void setVolumeSize(Integer volumeSize) {
		this.volumeSize = volumeSize;
	}
	public Integer getFreeSize() {
		return freeSize;
	}
	public void setFreeSize(Integer freeSize) {
		this.freeSize = freeSize;
	}
}
