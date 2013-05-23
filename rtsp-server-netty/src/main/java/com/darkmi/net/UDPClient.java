package com.darkmi.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket();
			String str = "hello World";
			InetAddress localhost = InetAddress.getByName("192.168.7.134");
			DatagramPacket packet = new DatagramPacket(str.getBytes(), str.length(), localhost, 3000);
			ds.send(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
