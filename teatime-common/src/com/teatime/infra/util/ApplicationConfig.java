package com.teatime.infra.util;

import java.util.Properties;

import com.teatime.common.util.FileUtil;
import com.teatime.common.util.LogUtil;

public class ApplicationConfig {

	public static final String DEFAULT_CONFIG_FILE = "conf/app.properties"; 
	public static Properties props = null;
	
	public static void loadConfig() {
		props = loadConfig(DEFAULT_CONFIG_FILE);
	}
	
	public static Properties loadConfig(String fn) {
		if(FileUtil.fileExists(DEFAULT_CONFIG_FILE)) {
			return ConfigUtil.loadConfigFromFile(DEFAULT_CONFIG_FILE);
		}
		else {
			LogUtil.error("ApplicationConfig - file doesn't exist: " + fn);
			return new Properties();
		}
	}
	
	public static String getStringProp(String key) {
		if(props == null)
			return null;
		else
			return props.getProperty(key);
	}
	
	public static Integer getIntegerProp(String key) {
		if(props == null)
			return null;
		else
			return Integer.parseInt(
					props.getProperty(key));
	}
	
	public static Long getLongProp(String key) {
		if(props == null)
			return null;
		else
			return Long.parseLong(
					props.getProperty(key));
	}
	
	public static Boolean getBooleanProp(String key) {
		if(props == null)
			return null;
		else
			return Boolean.parseBoolean(
					props.getProperty(key));
	}
	
	public static void main(String[] args) {
		System.out.println(ApplicationConfig.getStringProp("app.id"));
	}
}
