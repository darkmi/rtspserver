package com.darkmi.vvs.rs.client;

import javax.ws.rs.core.MultivaluedMap;


import com.darkmi.vvs.rs.dto.TransferContenDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import flexjson.JSONException;

/**
 * 使用Jersey Client的User REST客户端.
 * 在Mini-Service演示的基础上添加更多演示.
 * 
 * @author calvin
 */
@SuppressWarnings("unused")
public class TransferContentClient {

	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] argc) throws JSONException {  
		    String url = "http://localhost:8080/rs/transfer/content";
	        Client c = Client.create();  
			WebResource r=c.resource("http://localhost:9998/helloworld");  
	        TransferContenDTO transferContenDTO = new TransferContenDTO();  
	        transferContenDTO.setAssetID("test1");
	        
	        Client client = Client.create();
	        WebResource webResource = client.resource(url + "/put");
	        MultivaluedMap queryParams = new MultivaluedMapImpl();
	        queryParams.add("studentid", "2");
	        queryParams.add("name", "nametest");
	        queryParams.add("dept", "depttest");
	        ClientResponse response = webResource.queryParams(queryParams).put(ClientResponse.class, "foo:test");
	        System.out.println("Response for put request: " + response.getStatus());
	    } 
}
