package com.eunwoosoft.comm.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlTest {

	public static void main(String[] args) {

		if( args.length < 1 ) {
			
		    System.out.println("Usage: java UrlTest <url>");
		    return;
		}

		try {
			
			URL url = new URL(args[0]);
			//URL url = new URL("https://192.168.1.123:8001/UbiForm");
			//URL url = new URL("https://www.ubireport.com/demo2/UbiForm");
			
			System.out.println("URL(" + args[0] + ") Connecting...");
			URLConnection uc = url.openConnection();

			uc.setDoOutput(true);
			uc.setDoInput(true);
			uc.setUseCaches(false);
				

			StringBuffer sb = new StringBuffer();

			System.out.println("Reading Contents...");
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

			int buffSize = 1024 * 8;
			char[] buff;
			int insize = 0;
			while ((insize = in.read(buff = new char[buffSize], 0, buffSize)) != -1) {
				
				sb.append((new String(buff, 0, insize)));
			}
			String receivestr = sb.toString().trim();
			
			System.out.println("URL Contects");
			System.out.println("------------------------------------------");
			System.out.println(receivestr);
			System.out.println("------------------------------------------");
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
	}
}
