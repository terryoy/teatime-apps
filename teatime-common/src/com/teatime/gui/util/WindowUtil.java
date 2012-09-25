package com.teatime.gui.util;

import javax.swing.UIManager;

import com.teatime.common.util.LogUtil;

/**
 * Handle GUI related tasks.
 * 
 * @author terryoy
 *
 */
public class WindowUtil {
	
	
	public static void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			LogUtil.error("Error setting native LAF: " + e);
		}
	}

	public static void setJavaLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			LogUtil.error("Error setting Java LAF: " + e);
		}
	}
	
	public static void setLookAndFeel(String className) {
		try {
			UIManager.setLookAndFeel(className);
		} catch (Exception e) {
			LogUtil.error("Error setting Java LAF: " + e);
		}
	}
}
