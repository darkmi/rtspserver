package com.darkmi.inet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			serverSocket = new ServerSocket(7888);
			System.out.println("Server is running on port 7888...");
			int i = 0;
			while (true) {
				socket = serverSocket.accept();
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				i++;
				String str = br.readLine();
				System.out.println("You are No." + i + " guest, and your IP is:" + socket.getInetAddress() + " " + str);
				str = str.toUpperCase();
				pw = new PrintWriter(socket.getOutputStream());
				pw.println(str);
				pw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
				}
			if (serverSocket != null)
				try {
					serverSocket.close();
				} catch (IOException e) {
				}
		}
	}

}
