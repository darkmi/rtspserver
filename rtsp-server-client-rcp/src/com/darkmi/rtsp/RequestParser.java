package com.darkmi.rtsp;

import java.util.Set;

public class RequestParser {
	private String uriPath;
	private String endpointName;
	private String mediaFile;
//	private static final String AUDIO_SUFFIX = "/audio";
//	private static final String VIDEO_SUFFIX = "/video";
	//private MediaType mediaType;

	public RequestParser(String uriPath, Set<String> endpointNames) {
		//		this.uriPath = uriPath;
		//
		//		String mediaPath = uriPath;
		//
		//		if (mediaPath.endsWith("/audio")) {
		//			mediaPath = mediaPath.substring(0, mediaPath.indexOf(AUDIO_SUFFIX));
		//			mediaType = MediaType.AUDIO;
		//		} else if (mediaPath.endsWith("/video")) {
		//			mediaPath = mediaPath.substring(0, mediaPath.indexOf(VIDEO_SUFFIX));
		//			mediaType = MediaType.VIDEO;
		//		}
		//
		//		for (String endptNameTmp : endpointNames) {
		//			if (mediaPath.startsWith(endptNameTmp)) {
		//				int index = mediaPath.indexOf("/", endptNameTmp.length());
		//				if (index != -1) {
		//					endpointName = mediaPath.substring(0, index);
		//					mediaFile = mediaPath.substring((index + 1), mediaPath.length());
		//				} else {
		//					endpointName = mediaPath;
		//				}
		//				break;
		//			}
		//
		//		}
	}

	public String getUriPath() {
		return uriPath;
	}

//	public MediaType getMediaType() {
//		return mediaType;
//	}

	public String getEndpointName() {
		return endpointName;
	}

	public String getMediaFile() {
		return mediaFile;
	}

}
