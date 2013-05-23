package com.darkmi.net;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class InetAddressExample {

	public static void main(String[] args) {

		try {
			//1.获取主机的网络接口列表
			//静态方法getNetworkInterface()返回一个列表,其中包含勒该主机每一个接口所对应的NetworkInterface类实例。
			Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
			if (interfaceList == null) {
				System.out.println("--No interface found--");
			} else {
				while (interfaceList.hasMoreElements()) {
					NetworkInterface iface = interfaceList.nextElement();
					System.out.println("interface " + iface.getName() + ":");
					Enumeration<InetAddress> addrList = iface.getInetAddresses();

					if (!addrList.hasMoreElements()) {
						System.out.println("\t(No address for this interface)");
					}
					while (addrList.hasMoreElements()) {
						InetAddress address = addrList.nextElement();
						System.out.print("\tAddress"
								+ ((address instanceof Inet4Address ? "(v4)"
										: (address instanceof Inet6Address ? "(v6)" : "(?)"))));
						System.out.println(": " + address.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}

		for (String host : args) {
			try {
				System.out.println(host + ":");
				InetAddress[] addressList = InetAddress.getAllByName(host);
				for (InetAddress address : addressList) {
					System.out.println("\t" + address.getHostName() + "/" + address.getHostAddress());
				}

			} catch (UnknownHostException e) {
				System.out.println("\tUnable tofind addrss for" + host);
			}
		}
	}
}
