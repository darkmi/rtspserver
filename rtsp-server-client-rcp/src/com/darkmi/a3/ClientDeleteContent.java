package com.darkmi.a3;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 删除指定内容.
 * @author Administrator
 *
 */
public class ClientDeleteContent {

	public static void main(String[] args) throws IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://192.168.7.134:9090/DeleteContent");
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<DeleteContent ");
		sb.append("providerID=\"comcast.com\" ");
		sb.append("assetID=\"BAAA100000000018377\" ");
		sb.append("volumeName=\"volumeA\" ");
		sb.append("reasonCode=\"201\"/>");

		StringEntity myEntity = new StringEntity(sb.toString(), "UTF-8");
		httppost.addHeader("Content-Type", "text/xml");
		httppost.addHeader("CSeq", "111");
		httppost.setEntity(myEntity);
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();
		InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "ISO-8859-1");
		char[] buff = new char[1024];
		int length = 0;
		while ((length = reader.read(buff)) != -1) {
			System.out.println(new String(buff, 0, length));
		}
		httpclient.getConnectionManager().shutdown();
	}
}
