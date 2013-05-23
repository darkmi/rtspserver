package com.darkmi.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest {

	public static void main(String[] args) {

		try {
			fastCopyFile(System.getProperty("user.dir") + "\\synmanager.log", "c:\\synmanager.log");
			copyFile(System.getProperty("user.dir") + "\\synmanager.log", "d:\\synmanager.log");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//	public static void NioCopyFile()
	//	{
	//		long startTime = System.currentTimeMillis();
	//		try
	//		{
	//			String path = System.getProperty("user.dir") + "\\synmanager.log";
	//			String dist = "c:\\synmanager.log";
	//			FileChannel in = new FileInputStream(path).getChannel();
	//			FileChannel out = new FileOutputStream(dist).getChannel();
	//			ByteBuffer buffer = ByteBuffer.allocate(1024);
	//			while(in.read(buffer) != -1)
	//			{
	//				buffer.flip();
	//				out.write(buffer);
	//				buffer.clear();
	//			}
	//			in.close();
	//			out.close();
	//		}
	//		catch(Exception e)
	//		{
	//			e.printStackTrace();
	//		}
	//		long endTime = System.currentTimeMillis();
	//		System.out.println("Nio copy:" + (endTime - startTime));
	//	}

	public static void fastCopyFile(String srcFile, String destFile) throws Exception {
		long startTime = System.currentTimeMillis();
		FileInputStream fin = new FileInputStream(srcFile);
		FileOutputStream fout = new FileOutputStream(destFile);

		FileChannel fcin = fin.getChannel();
		FileChannel fcout = fout.getChannel();

		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

		while (true) {
			buffer.clear();

			int r = fcin.read(buffer);

			if (r == -1) {
				break;
			}

			buffer.flip();

			fcout.write(buffer);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Fast copy:" + (endTime - startTime));
	}

	public static void copyFile(String srcFile, String destFile) throws Exception {
		long startTime = System.currentTimeMillis();
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			File src = new File(srcFile);
			File dest = new File(destFile);

			in = new FileInputStream(src);
			out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];
			int i = 0;
			while ((i = in.read(buffer)) != -1) {
				out.write(buffer, 0, i);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Io copy:" + (endTime - startTime));
	}

}
