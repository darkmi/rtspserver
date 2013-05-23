package com.darkmi.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ReadFileBuff {
	public static void main(String args[]) throws IOException {
		if (args.length != 0) {
			String filename = args[0];
			FileInputStream fis = new FileInputStream(filename);
			FileChannel channel = fis.getChannel();
			int length = (int) channel.size();
			MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
			Charset charset = Charset.forName("ISO-8859-1");
			CharsetDecoder decoder = charset.newDecoder();
			CharBuffer charBuffer = decoder.decode(byteBuffer);
			for (int i = 0, n = charBuffer.length(); i < n; i++) {
				System.out.print(charBuffer.get());
			}
		}
	}
}
