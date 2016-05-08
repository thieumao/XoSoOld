package com.cudo.ketqua.xoso.soicau;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpURLConnection {
	public static String sendGet(String url) throws Exception {
		String USER_AGENT = "Mozilla/5.0";
		URL obj = new URL(url);
		java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj
				.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		return response.toString();

	}
}
