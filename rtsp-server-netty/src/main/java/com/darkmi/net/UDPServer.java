package com.darkmi.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket(3000);
			byte[] buf = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buf, 1024);
			ds.receive(packet);
			String strRecv = new String(packet.getData(), 0, packet.getLength());
			System.out.println("服务端接收到数据 --> " + strRecv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
