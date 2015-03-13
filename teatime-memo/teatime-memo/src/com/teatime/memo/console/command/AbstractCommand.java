/**
 * 
 */
package com.teatime.memo.console.command;

import com.teatime.memo.console.Command;
import com.teatime.memo.console.HelpDescriptor;

/**
 * @author Terry Ouyang
 *
 */
public abstract class AbstractCommand implements Command {
	private final HelpDescriptor helpDescriptor = initDescriptor();

	/**
	 * @return
	 */
	abstract protected HelpDescriptor initDescriptor();

	/**
	 * @return the descriptor
	 */
	public HelpDescriptor getHelpDescriptior() {
		return helpDescriptor;
	}
}
