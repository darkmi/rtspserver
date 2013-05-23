package com.darkmi.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient implements Runnable {

	Socket socket;
	BufferedReader reader;
	PrintWriter writer;

	@Override
	public void run() {
		try {
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 8060);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
			writer.println("aaa");
			writer.flush();
			while (true) {
				String inStr = reader.readLine();
				System.out.println("客户端接收到字符串 --> " + inStr);
				if (inStr == null) {
					writer.println("aaa");
					writer.flush();					
				} else {
					String outStr = inStr.toLowerCase();
					System.out.println("客户端发送 的字符串 --> " + outStr);
					writer.println(outStr.toLowerCase());
					writer.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Thread(new TCPClient()).start();
	}

}
