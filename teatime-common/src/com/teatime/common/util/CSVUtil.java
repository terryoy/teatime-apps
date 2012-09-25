package com.teatime.common.util;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilities for handling CSV file.
 * 
 * @author terryoy
 *
 */
public class CSVUtil {
	
	/**
	 * Normalize csv cell string. If there is "comma" in the string, add quotation mark to begin and end.
	 * 
	 * @param s
	 */
	public static String csvNormString(String s) {
		if(s.contains(","))
			return "\"" + s + "\"";
		else
			return s;
	}
	
	public static String[] parseCSVLine(String line) {
		List<String> strlist = new LinkedList<String>();
		String[] array = line.split(",");
		
		String curr = "";
		boolean connect = false;
		for(String s: array) {
			
			if(s.startsWith("\"")) { // if starts with "\""
				connect = true;
				curr = s.substring(1); 
			}
			else if(connect == true) { // if not starts with "\"" and connect == true
				
				if(s.endsWith("\"")) { // if ends with "\""
					curr+=s.substring(0, s.length() - 1); // finalize the string
					strlist.add(curr);
					
					// re-initialize
					curr = "";
					connect = false;
				}
				else { // segment not yet end
					curr += s;
				}
			}
			else if(connect == false){ // normal string
				strlist.add(s);
			}
		}
		
		return strlist.toArray(new String[0]);
	}
}
