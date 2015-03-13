/**
 * 
 */
package com.teatime.memo.console.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Terry Ouyang
 *
 */
public class CommandUtil {
	
	private static final String S_LAST = "last";
	private static final String S_YESTERDAY = "yesterday";
	

	public static Date getEntryDateFromCommandLine(String commandLine) {

		String[] args = commandLine.split(" ");
		Date entryDate = null;
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();

		// if there is a specific date string
		if(args.length >= 2) {
			String dateString = args[1];
			
			if(S_LAST.equalsIgnoreCase(dateString) || 
					S_YESTERDAY.equalsIgnoreCase(dateString)) {
				// set to yesterday
				calendar.add(Calendar.DATE, -1);
			} 
			else {
				// set to the specific date
				try {
					calendar.setTime(fmt.parse(args[1]));
				} catch (ParseException e) {
					// use default(today) instead
				}
			}
		}
		
		entryDate = calendar.getTime();
		
		return entryDate;
	}
}
