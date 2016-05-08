package com.cudo.ketqua.xoso.soicau;

import java.io.UnsupportedEncodingException;

import android.util.Base64;

public class maocrypto {
	public static String encodebase64(String text) {
		String base64 = "";
		byte[] data;
		try {
			data = text.getBytes("UTF-8");
			base64 = Base64.encodeToString(data, Base64.DEFAULT);
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		return base64;
	}

	public static String decodebase64(String text) {
		String base64 = "";
		byte[] data;
		data = Base64.decode(text, Base64.DEFAULT);
		try {
			base64 = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return base64;
	}
	
	public static String baencodebase64(String text) {
		String base64 = "";
		byte[] data;
		try {
			data = text.getBytes("UTF-8");
			base64 = Base64.encodeToString(data, Base64.DEFAULT);
			data = base64.getBytes("UTF-8");
			base64 = Base64.encodeToString(data, Base64.DEFAULT);
			data = base64.getBytes("UTF-8");
			base64 = Base64.encodeToString(data, Base64.DEFAULT);
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		return base64;
	}

	public static String badecodebase64(String text) {
		String base64 = "";
		byte[] data;
		data = Base64.decode(text, Base64.DEFAULT);
		try {
			base64 = new String(data, "UTF-8");
			data = Base64.decode(base64, Base64.DEFAULT);
			base64 = new String(data, "UTF-8");
			data = Base64.decode(base64, Base64.DEFAULT);
			base64 = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return base64;
	}
}
