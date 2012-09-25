package com.teatime.infra.util;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Handle system configurations for the applicaton.
 * 
 * @author terryoy
 *
 */
public class SystemConfig {
	
	public static Preferences prefs = null;

	/**
	 * Must initialize in application before using it.
	 * 
	 * @param clazz
	 */
	public static void initSystemConfig(Class clazz) {
		prefs = Preferences.systemNodeForPackage(clazz);
	}

	public static void clear() throws BackingStoreException {
		prefs.clear();
	}

	public static void flush() throws BackingStoreException {
		prefs.flush();
	}

	public static String get(String key, String def) {
		return prefs.get(key, def);
	}

	public static boolean getBoolean(String key, boolean def) {
		return prefs.getBoolean(key, def);
	}

	public static byte[] getByteArray(String key, byte[] def) {
		return prefs.getByteArray(key, def);
	}

	public static double getDouble(String key, double def) {
		return prefs.getDouble(key, def);
	}

	public static float getFloat(String key, float def) {
		return prefs.getFloat(key, def);
	}

	public static int getInt(String key, int def) {
		return prefs.getInt(key, def);
	}

	public static long getLong(String key, long def) {
		return prefs.getLong(key, def);
	}

	public static boolean isUserNode() {
		return prefs.isUserNode();
	}

	public static String[] keys() throws BackingStoreException {
		return prefs.keys();
	}

	public static String name() {
		return prefs.name();
	}

	public static Preferences node(String pathName) {
		return prefs.node(pathName);
	}

	public static boolean nodeExists(String pathName) throws BackingStoreException {
		return prefs.nodeExists(pathName);
	}

	public static void put(String key, String value) {
		prefs.put(key, value);
	}

	public static void putBoolean(String key, boolean value) {
		prefs.putBoolean(key, value);
	}

	public static void putByteArray(String key, byte[] value) {
		prefs.putByteArray(key, value);
	}

	public static void putDouble(String key, double value) {
		prefs.putDouble(key, value);
	}

	public static void putFloat(String key, float value) {
		prefs.putFloat(key, value);
	}

	public static void putInt(String key, int value) {
		prefs.putInt(key, value);
	}

	public static void putLong(String key, long value) {
		prefs.putLong(key, value);
	}

	public static void remove(String key) {
		prefs.remove(key);
	}

	public static void sync() throws BackingStoreException {
		prefs.sync();
	}
	
	
}
