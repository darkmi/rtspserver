package com.darkmi.nio.channel;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TCPEchoClientNonblocking {

	public static void main(String args[]) throws Exception {

		//if ((args.length < 2) || (args.length > 3)) // Test for correct # of args
		//	throw new IllegalArgumentException("Parameter(s): <Server> <Word> [<Port>]");
		//String server = args[0]; // Server name or IP address
		// Convert input String to bytes using the default charset
		//byte[] argument = args[1].getBytes();
		//int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

		String server = "localhost";
		int servPort = 3344;
		byte[] argument = "helloworld".getBytes();

		// Create channel and set to nonblocking
		SocketChannel clntChan = SocketChannel.open();
		clntChan.configureBlocking(false);

		// Initiate connection to server and repeatedly poll until complete
		if (!clntChan.connect(new InetSocketAddress(server, servPort))) {
			while (!clntChan.finishConnect()) {
				System.out.print("."); // Do something else
			}
		}
		ByteBuffer writeBuf = ByteBuffer.wrap(argument);
		ByteBuffer readBuf = ByteBuffer.allocate(argument.length);
		int totalBytesRcvd = 0; // Total bytes received so far
		int bytesRcvd; // Bytes received in last read
		
		while (totalBytesRcvd < argument.length) {
			if (writeBuf.hasRemaining()) {
				clntChan.write(writeBuf);
			}
			if ((bytesRcvd = clntChan.read(readBuf)) == -1) {
				throw new SocketException("Connection closed prematurely");
			}
			totalBytesRcvd += bytesRcvd;
			System.out.print("."); // Do something else
		}

		System.out.println("Received: " + new String(readBuf.array(), 0, totalBytesRcvd));
		clntChan.close();
	}
}
