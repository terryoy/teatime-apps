package com.teatime.net.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import com.teatime.common.util.LogUtil;

public class HTTPUtil {
	
	private static final String DEFAULT_ENCODING = "utf-8";
	private static final int DEFAULT_BUFFER_SIZE = 128*1024;
	private static final int READ_TIME_OUT_LIMIT = 60*5*1000;
	private static final int CONNECTION_TIME_OUT_LIMIT = 60*1000;
	
	public static String urlEncode(String raw, String enc) {
		String result = null;
		try {
			if(enc == null)
				result = URLEncoder.encode(raw);
			else
				result = URLEncoder.encode(raw, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String urlDecode(String raw, String enc) {
		String result = null;
		try {
			if(enc == null) 
				result = URLDecoder.decode(raw);
			else
				result = URLDecoder.decode(raw, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Open URL connection with a set of default configurations.
	 * 
	 * @param url
	 * @return
	 */
	public static URLConnection openURLConnection(String url) {
		return openURLConnection(url, null);
	}
	
	/**
	 * Open URL connection with a set of default configurations, and a set of headers.
	 * 
	 * @param url
	 * @return
	 */
	public static URLConnection openURLConnection(String url, Map<String, String> headerMap) {
		URLConnection conn;
		try {
			conn = new URL(url).openConnection();
			conn.setConnectTimeout(CONNECTION_TIME_OUT_LIMIT); // set default time out
			conn.setReadTimeout(READ_TIME_OUT_LIMIT); // set default time out
			
			if(headerMap != null) { // set HTTP header if neccessary
				for(Entry<String, String> headerEntry : headerMap.entrySet()) {
					conn.setRequestProperty(headerEntry.getKey(),
							headerEntry.getValue());
				}
			}
			
			return conn;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Download HTML content as String result, with the specific encoding(utf-8).
	 * 
	 * @param requestedUrl
	 * @return
	 */
	public static String downloadHTMLContent(String requestedUrl) {
		return downloadHTMLContent(requestedUrl, null);
	}
	
	/**
	 * Download HTML content as String result, with the specific encoding("utf-8" if null).
	 * 
	 * @param requestedUrl
	 * @param charset
	 * @return
	 */
	public static String downloadHTMLContent(String requestedUrl, String charset) {
		// content encoding
		return downloadHTMLContent(requestedUrl, charset, null);
	}
	
	/**
	 * Download HTML content as String result, with the specific encoding("utf-8" if null).
	 * 
	 * @param requestedUrl
	 * @param charset
	 * @return
	 */
	public static String downloadHTMLContent(String requestedUrl, String charset, Map<String, String> headerMap) {
		// content encoding
		String encoding = (charset == null) ? DEFAULT_ENCODING : charset;
		
		StringBuffer strBuf = new StringBuffer(DEFAULT_BUFFER_SIZE);
		try {
			
			URLConnection netConnection = openURLConnection(requestedUrl, headerMap);
			InputStream ins = netConnection.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(ins, encoding));

			char[] buffer = new char[4096];
			int length = 0;
			
			while( (length = reader.read(buffer)) > 0){
				strBuf.append(buffer,0,length);
			}
			
			reader.close();
			ins.close();
		} catch(FileNotFoundException e) {
			// file not found, return emtpy strBuf.toString() 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return strBuf.toString();
	}
}
