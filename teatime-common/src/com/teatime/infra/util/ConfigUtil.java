package com.teatime.infra.util;

import java.io.FileReader;
import java.util.Properties;

public class ConfigUtil {

	
	public static Properties loadConfigFromFile(String fn) {
		Properties props = new Properties();
		FileReader fr;
		try {
			fr = new FileReader(fn);
			props.load(fr);
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}
}
