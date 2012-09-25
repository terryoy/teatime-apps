package com.teatime.common.util;

public class StringUtil {

	/**
	 * Join the string segments with delimiter.
	 * 
	 * @param s
	 * @param delimiter
	 * @return
	 */
	public static String join(String[] s, String delimiter) {
		if(s.length > 0) {
			StringBuffer buf = new StringBuffer();
			buf.append(s[0]);
			for(int i=1; i < s.length; i++) {
				buf.append(delimiter);
				buf.append(s[i]);
			}
			return buf.toString();
		}
		else {
			return "";
		}
	}
	
	/**
	 * Check if the String is empty.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if(s == null || s.equals("")) 
			return true;
		else
			return false;
	}
}
