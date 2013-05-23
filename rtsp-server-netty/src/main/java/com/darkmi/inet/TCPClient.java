package com.darkmi.inet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
	public static void main(String[] args) {
		Socket s = null;
		BufferedReader br = null;
		BufferedReader brin = null;
		PrintWriter pw = null;

		try {
			s = new Socket("127.0.0.1", 7888);
			System.out.println("Please enter something:");
			brin = new BufferedReader(new InputStreamReader(System.in));
			String strin = brin.readLine();
			pw = new PrintWriter(s.getOutputStream());
			pw.println(strin);
			pw.flush();
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = br.readLine();
			System.out.println(str);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
			if (brin != null)
				try {
					brin.close();
				} catch (IOException e) {
				}
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
				}
		}
	}

}
