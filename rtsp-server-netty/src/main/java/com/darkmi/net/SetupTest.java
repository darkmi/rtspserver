package com.darkmi.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class SetupTest {

	public static void main(String[] args) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("SETUP rtsp://192.168.7.134:555/movie---2142---movie_387 RTSP/1.0\r\n");
		sb.append("CSeq: 1\r\n");
		sb.append("Require: HFC.Delivery.Profile.1.0\r\n");
		sb.append("May-Notify: \r\n");
		sb.append("Transport: MP2T/AVP;destination=192.168.14.102;port=49156;unicast\r\n");
		sb.append("\r\n\r\n");
		System.out.println(sb.toString());
		
		byte[] data = new byte[1024];

		Socket s = new Socket("192.168.7.134", 555);

		InputStream in = s.getInputStream();
		OutputStream out = s.getOutputStream();

		out.write(sb.toString().getBytes());
		int totalBytesRcvd = 0;
		int bytesRcvd;
		while (totalBytesRcvd < data.length) {
			if ((bytesRcvd = in.read(data, totalBytesRcvd, data.length - totalBytesRcvd)) == -1) {
				throw new SocketException("Connection closed prematurely");
			}
			totalBytesRcvd += bytesRcvd;
		}
		System.out.println("Receved: " + new String(data));
		s.close();
	}

}
