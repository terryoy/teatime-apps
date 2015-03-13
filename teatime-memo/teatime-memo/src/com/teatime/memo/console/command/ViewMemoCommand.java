/**
 * 
 */
package com.teatime.memo.console.command;

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
public class ViewMemoCommand extends AbstractCommand {
	
	private MemoEntryService memoEntryService;
	
	/**
	 * @param memoEntryService
	 */
	public ViewMemoCommand(MemoEntryService memoEntryService) {
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
		
		MemoEntry memoEntry = null;
		try {
			// find existing memo
			memoEntry = this.memoEntryService.getMemoEntryByDate(entryDate);
		} catch (ResourceAccessException e) {
		}
		
		if(memoEntry == null) {
			System.out.println("Cannot find memo for " + MemoEntryConstants.
					EntryDateTagDafaultFormatter.format(entryDate) + ".");
		}
		else {
			System.out.println(
				MemoEntryConstants.EntryDateTagDafaultFormatter
				.format(memoEntry.getDate()));
			System.out.println(
					memoEntry.getContent());
			
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#getCommandName()
	 */
	public String getIdentifier() {
		return "view";
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.command.AbstractCommand#initDescriptor()
	 */
	@Override
	protected HelpDescriptor initDescriptor() {
		return HelpDescriptor.createHelpDescriptor("view [yyyy-mm-dd] | last | yesterday", 
				"view memo by date(if the date is not specified, view today's)");
	}

}
