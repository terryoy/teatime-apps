/**
 * 
 */
package com.teatime.memo.console.command;

import com.teatime.memo.console.HelpDescriptor;

/**
 * The command to exit console(similar to Quit command).
 * 
 * @author Terry Ouyang
 */
public class ExitCommand extends AbstractCommand {

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#executeCommand(java.lang.String)
	 */
	public boolean executeCommand(String commandLine) {
		return true;
	}


	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#getIdentifier()
	 */
	public String getIdentifier() {
		return "exit";
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.command.AbstractCommand#initDescriptor()
	 */
	@Override
	protected HelpDescriptor initDescriptor() {
		return HelpDescriptor.createHelpDescriptor("exit", 
		"exit the program(same as \"quit\")");
	}

}
