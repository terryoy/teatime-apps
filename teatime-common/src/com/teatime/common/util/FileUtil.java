package com.teatime.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilities for file operations.
 * 
 * @author terryoy
 *
 */
public class FileUtil {

	private static final String DEFAULT_ENCODING = "utf-8";
	private static final int DEFAULT_BUFFER_SIZE = 128*1024;
	
	public static String readFileAsString(String fileName) {
		return readFileAsString(fileName, DEFAULT_ENCODING);
	}

	public static String readFileAsString(String fileName, String charset) {
		// content encoding
		String encoding = (charset == null) ? DEFAULT_ENCODING : charset;
		
		StringBuffer strBuf = new StringBuffer(DEFAULT_BUFFER_SIZE);
		try {
			FileInputStream finStrm = new FileInputStream(fileName); 
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(finStrm, encoding));

			char[] buffer = new char[4096];
			int length = 0;
			
			while( (length = reader.read(buffer)) > 0){
				strBuf.append(buffer,0,length);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return strBuf.toString();
	}
	
	public static void writeStringToFile(String content, String filename) {
		writeStringToFile(content, filename, false);
	}
	
	public static void writeStringToFile(String content, String filename, boolean append) {
		FileWriter fw;
		try {
			fw = new FileWriter(filename, append);
			fw.append(content);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean fileExists(String fn) {
		File file = new File(fn);
		return file.exists();
	}

	public static void createDirs(String filePath) {
		File dir = new File(filePath);
		dir.mkdirs();
	}

	public static boolean checkFileSizeExist(String fn, long size) {
		File file = new File(fn);
		if(file.exists() == true && file.isFile() && file.length() == size)
			return true;
		else
			return false;
	}
	
}
