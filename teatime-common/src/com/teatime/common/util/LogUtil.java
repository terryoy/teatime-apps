package com.teatime.common.util;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.teatime.infra.util.ApplicationConfig;

/**
 * Common log output
 * 
 * @author terryoy
 *
 */
public class LogUtil {

	public static final PrintStream CONSOLE = System.out;
	
	public static final int LOG_LEVEL_DEBUG = 1;
	public static final int LOG_LEVEL_INFO = 2;
	public static final int LOG_LEVEL_ERROR = 5;
	
	/* static variables */
	public static int LogLevel = getAppLogLevel();
	public static PrintStream LogAppender = CONSOLE;
	public static DateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	
	public static void debug(String msg) {
		if(LogLevel <= LOG_LEVEL_DEBUG)
			LogAppender.println(currTimeStamp() + " " + msg);
	}
	
	private static int getAppLogLevel() {
		String logLevel = ApplicationConfig.getStringProp("teatime.common.loglevel");
		if("debug".equalsIgnoreCase(logLevel)) {
			return LOG_LEVEL_DEBUG;
		}
		else if("info".equalsIgnoreCase(logLevel)) {
			return LOG_LEVEL_INFO;
		}
		else if("error".equalsIgnoreCase(logLevel)) {
			return LOG_LEVEL_ERROR;
		}
		else {
			return LOG_LEVEL_DEBUG;
		}
	}

	public static void error(String msg) {
		if(LogLevel <= LOG_LEVEL_ERROR)
			LogAppender.println(currTimeStamp() + " " + msg);
	}

	public static void info(String msg) {
		if(LogLevel <= LOG_LEVEL_INFO)
			LogAppender.println(currTimeStamp() + " " + msg);
	}
	
	private static String currTimeStamp() {
		return dateFmt.format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(dateFmt.format(new Date()));
		
		System.out.println("".equalsIgnoreCase(null));
	}
}
