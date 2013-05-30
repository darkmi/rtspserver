package com.darkmi.vvs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import jodd.io.http.Http;
import jodd.io.http.HttpParams;
import jodd.io.http.HttpTransfer;
import jodd.util.StringTemplateParser;
import junit.framework.TestCase;

public class InterfaceTest extends TestCase {

	public InterfaceTest(String testName) {
		super(testName);
	}

	public void login() throws Exception {
		HttpTransfer request = Http.createRequest("GET", "http://www.baidu.com/");
		HttpParams httpParams = new HttpParams();
		httpParams.setParameter("id", "173");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	@SuppressWarnings("unused")
	public void receive() throws Exception {

		StringTemplateParser stp = new StringTemplateParser();
		String content = "";

	}

	/**
	 * @param request
	 * @param httpParams
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void send(HttpTransfer request, HttpParams httpParams) throws UnknownHostException, IOException {
		setHeader(request);
		request.setQueryParameters(httpParams);
		Socket socket = new Socket(request.getHost(), request.getPort());
		OutputStream out = socket.getOutputStream();
		request.send(out);
		InputStream in = socket.getInputStream();
		HttpTransfer response = Http.readResponse(in);
		System.out.println(response.toString());
		socket.close();
	}

	/**
	 * @param request
	 */
	private void setHeader(HttpTransfer request) {
		request.addHeader("ab", "1000");
		request.addHeader("User-Agent", "jodd");
		request.addHeader("User-Agent", "jodd");
		request.addHeader("User-Agent", "jodd");
		request.addHeader("User-Agent", "jodd");
		request.addHeader("User-Agent", "jodd");
		request.addHeader("User-Agent", "jodd");
	}

}
