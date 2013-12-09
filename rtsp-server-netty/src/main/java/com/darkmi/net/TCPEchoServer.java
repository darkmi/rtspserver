package com.darkmi.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPEchoServer {
	public static void main(String[] args) throws Exception {
		int recvMsgSize;
		ServerSocket servSock = new ServerSocket(8989);
		byte[] receiveBuf = new byte[1024];
		while (true) {
			Socket clntSock = servSock.accept();
			SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
			System.out.println("Handling client at " + clientAddress);

			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();

			while ((recvMsgSize = in.read(receiveBuf)) != -1) {
				String reveMsgStr = new String(receiveBuf, 0, recvMsgSize);
				System.out.println("client send message : " + reveMsgStr);
				out.write("hello client.".getBytes());
			}

			clntSock.close();
		}
	}
}