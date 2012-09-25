package com.teatime.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * For handling date time.
 * 
 * @author terryoy
 *
 */
public class DateTimeUtil {

	private static final DateFormat dateFmt = new SimpleDateFormat("yyyy-MM hh:mm:ss");
	
	public static String getSimpleTime(Date date) {
		return dateFmt.format(date);
	}
	
	public static String getFormatTime(Date date, String format) {
		DateFormat fmt = getDateFormat(format);
		return fmt.format(date);
	}
	
	public static DateFormat getDateFormat(String fmt) {
		return new SimpleDateFormat(fmt);
	}
	
	
	public static String getSimpleNowTime() {
		return DateTimeUtil.getSimpleTime(new Date());
	}
	
}
