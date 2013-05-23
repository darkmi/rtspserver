package com.darkmi.vvs.rs.client;

import org.springframework.beans.factory.annotation.Required;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * 使用Jersey Client的User REST客户端.
 * 在Mini-Service演示的基础上添加更多演示.
 * 
 * @author calvin
 */
public class TransferStatusClient {


	private WebResource client;

	@Required
	public void setBaseUrl(String baseUrl) {
		Client jerseyClient = Client.create(new DefaultClientConfig());
		client = jerseyClient.resource(baseUrl);
	}
	/**
	 * 返回html格式的特定内容.
	 */
	public String transferStatus(String name) {
		return client.path("/users/search").queryParam("name", name).queryParam("format", "html").get(String.class);
	}



}
