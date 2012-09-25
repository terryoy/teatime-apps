package com.teatime.net.util;

import java.util.Map;
import java.util.Map.Entry;

public class WebAPIUtil {

	/**
	 * Return a url parameter string begins with "?" according to the input map
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String getParameterString(Map<String, String> paramMap) {
		StringBuffer buf = new StringBuffer();
		buf.append("?");
		for(Entry<String, String> entry: paramMap.entrySet()) {
			buf.append(entry.getKey() + "=" + 
					HTTPUtil.urlEncode(entry.getValue(), null) + "&");
		}
		
		return buf.toString();
	}
}
