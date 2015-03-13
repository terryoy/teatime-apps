/**
 * 
 */
package com.teatime.memo.console.command;

import com.teatime.memo.console.HelpDescriptor;

/**
 * The command o quit the program.
 * 
 * @author Terry Ouyang
 *
 */
public class QuitCommand extends AbstractCommand  {

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#executeCommand(java.lang.String)
	 */
	public boolean executeCommand(String commandLine) {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.command.AbstractCommand#initDescriptor()
	 */
	@Override
	protected HelpDescriptor initDescriptor() {
		return HelpDescriptor.createHelpDescriptor("quit", 
				"to quit the program(same as \"exit\")");
	}

	/* (non-Javadoc)
	 * @see com.teatime.memo.console.Command#getName()
	 */
	public String getIdentifier() {
		return "quit";
	}

}
