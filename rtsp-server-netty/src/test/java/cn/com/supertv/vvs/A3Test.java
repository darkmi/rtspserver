package cn.com.supertv.vvs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import jodd.io.http.Http;
import jodd.io.http.HttpParams;
import jodd.io.http.HttpTransfer;
import junit.framework.TestCase;

/**
 * @Description 测试A3接口
 * @author slaton.wu@gmail.com
 * @date 2012-7-25上午9:24:36
 * 
 */
public class A3Test extends TestCase {

	public void getContentAllInfo() throws Exception {
		HttpTransfer request = Http.createRequest("GET", "http://localhost/vsp2/rest/vsp/getContentAllInfo/3");
		HttpParams httpParams = new HttpParams();
		// httpParams.setParameter("id", "173");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	/**
	 * 获取单个内容信息
	 * @throws Exception
	 */
	public void getContentInfo() throws Exception {
		StringBuffer sb = new StringBuffer("http://localhost/vsp2/rest/vsp/getContentInfo/3/supertv.com/supertv001");
		//sb.append("RivercastTechnology.volume1");
		HttpTransfer request = Http.createRequest("GET", sb.toString());
		HttpParams httpParams = new HttpParams();
		// httpParams.setParameter("id", "173");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	public void deleteAllContent() throws Exception {
		HttpTransfer request = Http.createRequest("POST", "http://localhost:8080/vsp2/rest/vsp/deleteAllContent");
		HttpParams httpParams = new HttpParams();
		httpParams.setParameter("providerID", "173");
		httpParams.setParameter("assetID", "173");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());
	}

	public void deleteContent() throws Exception {
		HttpTransfer request = Http.createRequest("POST", "http://localhost:8080/vsp2/rest/vsp/deleteContent");
		HttpParams httpParams = new HttpParams();
		httpParams.setParameter("providerID", "173");
		httpParams.setParameter("assetID", "173");
		httpParams.setParameter("vsid", "3");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	public void cancelTransfer() throws Exception {
		HttpTransfer request = Http.createRequest("POST", "http://localhost:8080/vsp2/rest/vsp/cancelTransfer");
		HttpParams httpParams = new HttpParams();
		// httpParams.setParameter("providerID", "173");
		// httpParams.setParameter("assetID", "173");
		// httpParams.setParameter("vsid", "3");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	public void getVolumeInfo() throws Exception {
		HttpTransfer request = Http.createRequest("GET", "http://localhost/vsp2/rest/vsp/getVolumeInfo/3");
		HttpParams httpParams = new HttpParams();
		// httpParams.setParameter("id", "173");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	public void getTransferStatus() throws Exception {
		HttpTransfer request = Http.createRequest("GET",
				"http://localhost/vsp2/rest/vsp/getTransferStatus/3/cqan.com/CQAN011");
		HttpParams httpParams = new HttpParams();
		// httpParams.setParameter("id", "173");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	public void transferContentFtp() throws Exception {
		HttpTransfer request = Http.createRequest("POST", "http://localhost/vsp2/rest/vsp/transferContentFtp");
		HttpParams httpParams = new HttpParams();
		httpParams.setParameter("providerID", "cqan.com");
		httpParams.setParameter("assetID", "CQAN007");
		httpParams.setParameter("transferBitRate", "350000");
		httpParams.setParameter("sourceURL", "ftp://192.168.14.207:21/movie/g/gongfuxiongmao2.ts");
		httpParams.setParameter("userName", "root");
		httpParams.setParameter("password", "123456");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	public void singleTransferContentFtp() throws Exception {
		HttpTransfer request = Http.createRequest("POST",
				"http://localhost:8080/vsp2/rest/vsp/singleTransferContentFtp");
		HttpParams httpParams = new HttpParams();
		httpParams.setParameter("providerID", "cqan.com");
		httpParams.setParameter("assetID", "CQAN001");
		httpParams.setParameter("transferBitRate", "350000");
		httpParams.setParameter("sourceURL", "173");
		httpParams.setParameter("userName", "root");
		httpParams.setParameter("password", "123456");
		httpParams.setParameter("vsid", "3");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	public void transferContentUdp() throws Exception {
		HttpTransfer request = Http.createRequest("POST", "http://localhost/vsp2/rest/vsp/transferContentUdp");
		HttpParams httpParams = new HttpParams();
		httpParams.setParameter("providerID", "cqan.com");
		httpParams.setParameter("assetID", "CQAN008");
		httpParams.setParameter("transferBitRate", "35000");
		httpParams.setParameter("sourceURL", "UDP://228.1.1.100:6002");
		httpParams.setParameter("sourceIP", "192.168.0.12:5000");
		httpParams.setParameter("captureStart", "2012-07-26T10:20:12Z");
		httpParams.setParameter("captureEnd", "2012-07-26T10:25:12Z");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	public void singleTransferContentUdp() throws Exception {
		HttpTransfer request = Http.createRequest("POST",
				"http://localhost:8080/vsp2/rest/vsp/singleTransferContentUdp/3");
		HttpParams httpParams = new HttpParams();
		httpParams.setParameter("providerID", "173");
		httpParams.setParameter("assetID", "173");
		httpParams.setParameter("transferBitRate", "173");
		httpParams.setParameter("sourceURL", "173");
		httpParams.setParameter("captureStart", "173");
		httpParams.setParameter("captureEnd", "173");
		httpParams.setParameter("channelId", "173");
		httpParams.setParameter("vsid", "173");
		send(request, httpParams);
		System.out.println(request.getQueryParameters());

	}

	/**
	 * @param request
	 * @param httpParams
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void send(HttpTransfer request, HttpParams httpParams) throws UnknownHostException, IOException {
		setHeader(request);
		request.setRequestParameters(httpParams);
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
	}
}
