package cn.com.supertv.vvs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import jodd.io.http.Http;
import jodd.io.http.HttpParams;
import jodd.io.http.HttpTransfer;

/**
 * Hello world!
 * 
 */
public class App {

	private static Set<String> ports = new HashSet<String>();

	public static void main(String[] args) {
		ports.add("6002");
		ports.add("6014");
		ports.add("6012");
		UdpTask task = new UdpTask();
		task.start();
	}

	public static void singleTransferContentUdp(int assetID) throws Exception {
		HttpTransfer request = Http.createRequest("POST", "http://localhost/vsp2/rest/vsp/transferContentUdp");
		HttpParams httpParams = new HttpParams();
		httpParams.setParameter("providerID", "supertv.com");
		httpParams.setParameter("assetID", "supertv00" + assetID);
		httpParams.setParameter("transferBitRate", "350000");
		httpParams.setParameter("sourceURL", "UDP://228.1.1.100:6002");
		httpParams.setParameter("sourceIP", "192.168.0.12:5000");
		System.out.println("开始时间:" + "2012-07-26T11:" + (assetID) + "0:12Z");
		System.out.println("结束时间:" + "2012-07-26T11:" + (assetID + 1) + "0:12Z");
		httpParams.setParameter("captureStart", "2012-07-26T11:" + (assetID) + "0:12Z");
		httpParams.setParameter("captureEnd", "2012-07-26T11:" + (assetID + 1) + "0:12Z");
		httpParams.setParameter("vsid", "3");
		send(request, httpParams);
		System.out.println(request.getRequestParameters());

	}

	/**
	 * @param request
	 * @param httpParams
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private static void send(HttpTransfer request, HttpParams httpParams) throws UnknownHostException, IOException {
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
	private static void setHeader(HttpTransfer request) {
		//request.addHeader("ab", "1000");
	}

	static class UdpTask implements Runnable {
		private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		int i = 2;

		@Override
		public void run() {
			try {
				singleTransferContentUdp(i);
				System.out.println(i);
				if (i == 3) {
					this.stop();
					System.out.println("执行完成！！");
				}
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void start() {
			if (scheduler.isShutdown()) {
				scheduler = Executors.newScheduledThreadPool(1);
			}
			scheduler.scheduleWithFixedDelay(this, 1, 10, TimeUnit.MINUTES);
			System.out.println("定时器已开启！");
		}

		public void stop() {
			scheduler.shutdown();
		}
	}
}
