package com.darkmi.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class TCPEchoClient {
	public static void main(String[] args) throws Exception {
		byte[] data = "hello server.".getBytes();
		Socket socket = new Socket("localhost", 8989);
		System.out.println("Connected to server... sending echo string");
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		out.write(data);

		int totalBytesRcvd = 0;
		int bytesRcvd;

		while (totalBytesRcvd < data.length) {
			if ((bytesRcvd = in.read(data, totalBytesRcvd, data.length - totalBytesRcvd)) == -1) {
				throw new SocketException("Connection closed prematurely");
			}
			totalBytesRcvd += bytesRcvd;
		}
		System.out.println("Receved: " + new String(data));
		socket.close();
	}
}
