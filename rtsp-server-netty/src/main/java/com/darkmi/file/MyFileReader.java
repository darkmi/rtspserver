package com.darkmi.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyFileReader {

	@SuppressWarnings("resource")
	public static void read() throws IOException {

		String fileName = "C:\\java\\EclipStudy\\workplace\\perf4jone\\src\\main\\java\\com\\darkmi\\file\\93.log";
		File file = new File(fileName);
		if (!file.exists() || file.isDirectory()) {
			throw new FileNotFoundException();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

		String line = null;
		line = br.readLine();
		while (line != null) {
			String[] temp = line.split(" - ");
			System.out.println(temp[1]);
			
			line = br.readLine();
		}
	}

	public static void main(String[] args) throws IOException {

		read();

	}

}
