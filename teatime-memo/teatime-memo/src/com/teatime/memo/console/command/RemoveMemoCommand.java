/**
 * 
 */
package com.teatime.memo.console.command;

import java.util.Date;
import java.util.Scanner;

import com.teatime.memo.console.HelpDescriptor;
import com.teatime.memo.domain.MemoEntryConstants;
import com.teatime.memo.service.MemoEntryService;
import com.teatime.memo.service.exception.ResourceAccessException;

/**
 * @author Terry Ouyang
 *
 */
public class RemoveMemoCommand extends AbstractCommand {
	
	private MemoEntryService memoEntryService;

	/**
	 * @param memoEntryService
	 */
	public RemoveMemoCommand(MemoEntryService memoEntryService) {
		// TODO Auto-generated constructor stub
		this.memoEntryService = memoEntryService;
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#executeCommand(java.lang.String)
	 */
	public boolean executeCommand(String commandLine) {
		Date entryDate = CommandUtil.getEntryDateFromCommandLine(commandLine);
		
		if(entryDate == null){
			System.out.println("parse date error!");
			return false;
		}
		
		String entryDateStr = 
			MemoEntryConstants.EntryDateTagDafaultFormatter.format(entryDate);
		System.out.println("Remove the memo of " + entryDateStr
				+ ", are you sure? (y/n)");
		Scanner scanner = new Scanner(System.in);
		if(!scanner.nextLine().equalsIgnoreCase("y")) {
			System.out.println("cancel removing.");
			return false;
		}
		
		try {
			this.memoEntryService.deleteMemoEntryByDate(entryDate);
		} catch (ResourceAccessException e) {
			System.out.println("remove memo error!");
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#getCommandName()
	 */
	public String getIdentifier() {
		return "remove";
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.command.AbstractCommand#initDescriptor()
	 */
	@Override
	protected HelpDescriptor initDescriptor() {
		return HelpDescriptor.createHelpDescriptor(
				"remove [yyyy-mm-dd] | last | yesterday", 
				"remove memo by date(if the date is not specified, remove today's)");
	}


}
