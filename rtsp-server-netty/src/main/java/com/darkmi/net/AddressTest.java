package com.darkmi.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AddressTest {

	public static void main(String args[]) {
		InetAddress adds = null;
		try {
			adds = InetAddress.getByName("www.baidu.com");
		} catch (UnknownHostException e) {
			System.out.println(adds);
		}
		System.out.println("" + adds);
	}

}
