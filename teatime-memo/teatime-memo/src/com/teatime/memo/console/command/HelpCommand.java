/**
 * 
 */
package com.teatime.memo.console.command;

import java.util.Iterator;

import com.teatime.memo.console.Command;
import com.teatime.memo.console.HelpDescriptor;
import com.teatime.memo.console.MemoCommandProcessor;

/**
 * @author Terry Ouyang
 *
 */
public class HelpCommand extends AbstractCommand {
	private MemoCommandProcessor controller;
	
	public HelpCommand(MemoCommandProcessor controller) {
		this.controller = controller;
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#executeCommand(java.lang.String)
	 */
	public boolean executeCommand(String commandLine) {
		System.out.println("usage: ");
		
		Iterator<Command> itr = this.controller
			.getCommandList()
			.iterator();
		while(itr.hasNext()) {
			Command command = itr.next();
			System.out.println(command.getHelpDescriptior().formatOutput());
		}
		System.out.println();
		return false;
	}


	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#getCommandName()
	 */
	public String getIdentifier() {
		return "help";
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.command.AbstractCommand#initDescriptor()
	 */
	@Override
	protected HelpDescriptor initDescriptor() {
		return HelpDescriptor.createHelpDescriptor("help", 
			"print console help");
	}

	
}
