package com.darkmi.apm.response;

import java.util.List;

public class ContentListResp {

	private List<ContentInfoResp> contentInfos;

	private String nextTag;

	public List<ContentInfoResp> getContentInfos() {
		return contentInfos;
	}

	public String getNextTag() {
		return nextTag;
	}

	public void setNextTag(String nextTag) {
		this.nextTag = nextTag;
	}

	public void setContentInfos(List<ContentInfoResp> contentInfos) {
		this.contentInfos = contentInfos;
	}

}
