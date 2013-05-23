package com.darkmi.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {
	Socket socket;
	
	public TCPServer(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			while (true) {
				String inStr = reader.readLine();
				if (inStr.equalsIgnoreCase("quit")) {
					break;
				}
				String outStr = inStr.toUpperCase() + System.getProperty("line.separator");
				dos.writeBytes(outStr);
			}
			reader.close();
			dos.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8060);
			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(new TCPServer(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
