package com.darkmi.vvs.rs.entity;

import cn.com.supertv.entity.MidEntity;

import com.google.code.morphia.annotations.Entity;
@Entity(value = "vvs_volume_info", noClassnameStored = true)
public class VolumeInfo  extends MidEntity {
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
	@Override
	public String toString() {
		return "VolumeInfo [volumeName=" + volumeName + ", state=" + state  + ", volumeSize="
				+ volumeSize + ", freeSize=" + freeSize + "]";
	}
}
