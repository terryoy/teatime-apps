/**
 * 
 */
package com.teatime.memo.console.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import com.teatime.memo.console.HelpDescriptor;
import com.teatime.memo.domain.MemoEntry;
import com.teatime.memo.domain.MemoEntryConstants;
import com.teatime.memo.service.MemoEntryService;
import com.teatime.memo.service.exception.ResourceAccessException;

/**
 * @author Terry Ouyang
 *
 */
public class WriteMemoCommand extends AbstractCommand {
	
	private MemoEntryService memoEntryService;
	
	private static final String SAVE_COMMAND = "-save";
	private static final String CANCEL_COMMAND = "-cancel";
	private static final String SEPARATOR = System.getProperty("line.separator");

	/**
	 * @param memoEntryService
	 */
	public WriteMemoCommand(MemoEntryService memoEntryService) {
		// TODO Auto-generated constructor stub
		this.memoEntryService = memoEntryService;
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#executeCommand(java.lang.String)
	 */
	public boolean executeCommand(String commandLine) {
		Date entryDate = CommandUtil.getEntryDateFromCommandLine(commandLine);
		if(entryDate == null) {
			System.out.println("parse date error! use today instead.");
			entryDate = new Date();
		}

		String dateStr = 
			MemoEntryConstants.EntryDateTagDafaultFormatter.format(entryDate);
		System.out.println(dateStr + " (note: \"-save\" to save, \"-cancel\" to cancel)");
		

		BufferedReader in = new BufferedReader(
				new InputStreamReader(System.in));
		StringBuffer buf = new StringBuffer();
		while(true) {
			String line = "";
			try {
				line = in.readLine();
			} catch (IOException e1) {
			}
			
			if(line.equalsIgnoreCase(CANCEL_COMMAND))
				// cancel the command
				break;
			else if(line.equals(SAVE_COMMAND)) {
				// save the memo entry
				MemoEntry entry = new MemoEntry();
				entry.setDate(entryDate);
				entry.setContent(buf.toString());
				try {
					this.memoEntryService.saveMemoEntry(entry);
				} catch (ResourceAccessException e) {
					System.out.println("write memo error!");
				}
				break;
			}
			else {
				// append the content
				buf.append(line + SEPARATOR);
			}
			
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#getName()
	 */
	public String getIdentifier() {
		return "write";
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.command.AbstractCommand#initDescriptor()
	 */
	@Override
	protected HelpDescriptor initDescriptor() {
		return HelpDescriptor.createHelpDescriptor("write [yyyy-mm-dd] | last | yesterday", 
				"write memo by date(if the date is not specified, write today's); " +
				"use \"-save\" to save the memo, use \"-cancel\" to cancel the operation");
	}

}
