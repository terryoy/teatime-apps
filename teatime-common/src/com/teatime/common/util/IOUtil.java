package com.teatime.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Utilities to handle the Input/Output stream related tasks.
 * 
 * @author terryoy
 *
 */
public class IOUtil {

	/**
	 * Create a file output stream by file name, not in append mode.
	 * 
	 * @param fn file name
	 * @return
	 */
	public static OutputStream getFileOutputStream(String fn) {
		return getFileOutputStream(fn, false);
	}
	
	/**
	 * Create a file output stream by file name, with the choice of append mode or not.
	 * 
	 * @param fn file name
	 * @param append
	 * @return
	 */
	public static OutputStream getFileOutputStream(String fn, boolean append) {
		
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(fn, append);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return fout;
		
	}
	
	public static FileReader getFileReader(String fn) {
		try {
			FileReader fr = new FileReader(fn);
			
			return fr;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static FileInputStream getFileInputStream(String fn) {
		try {
			FileInputStream fr = new FileInputStream(fn);
			
			return fr;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static PrintWriter getFilePrintWriter(String fn, boolean append) {
		try {
			PrintWriter fw = new PrintWriter(new FileWriter(fn, append));
			return fw;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static PrintWriter getFilePrintWriter(String fn) {
		return getFilePrintWriter(fn, false);
	}
}
