/**
 * 
 */
package com.teatime.memo.console;

/**
 * @author Terry Ouyang
 *
 */
public interface Command {

	public String getIdentifier();
	public HelpDescriptor getHelpDescriptior();
	
	/**
	 * @param commandLine
	 * @return the boolean flag denotes whether to 
	 * terminate the console loop or not
	 */
	public boolean executeCommand(String commandLine);
	
}
