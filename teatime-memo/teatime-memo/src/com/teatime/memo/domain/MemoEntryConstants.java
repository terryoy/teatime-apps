/**
 * 
 */
package com.teatime.memo.domain;

import java.text.SimpleDateFormat;

/**
 * @author Terry Ouyang
 *
 */
public class MemoEntryConstants {

	public static final String ENTRY_DATE_START_TAG = "[";
	
	public static final String ENTRY_DATE_END_TAG = "]";
	
	public static final String END_OF_ENTRY_LINE = "--- END OF ENTRY ---";
	
	public static final String ENTRY_DATE_TAG_PATTERN =
		ENTRY_DATE_START_TAG + "yyyy-MM-dd" + ENTRY_DATE_END_TAG;
	
	public static final SimpleDateFormat EntryDateTagDafaultFormatter = 
		new SimpleDateFormat(ENTRY_DATE_TAG_PATTERN);
}
