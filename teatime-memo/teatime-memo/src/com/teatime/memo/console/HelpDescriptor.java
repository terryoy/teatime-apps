/**
 * 
 */
package com.teatime.memo.console;

/**
 * The help descriptor for a <b>Command</b>. It is read-only
 * except the constructor method.
 * 
 * @author Terry Ouyang
 *
 */
public class HelpDescriptor {

	private String commandExpression;
	private String commandDescription;
	
	/**
	 * @return the commandExpression
	 */
	public String getCommandExpression() {
		return commandExpression;
	}

	/**
	 * @return the commandDescription
	 */
	public String getCommandDescription() {
		return commandDescription;
	}
	
	public static HelpDescriptor createHelpDescriptor(
			String cmdExpression, String cmdDescription) {
		
		HelpDescriptor descriptor = new HelpDescriptor();
		descriptor.commandExpression = cmdExpression;
		descriptor.commandDescription = cmdDescription;
		
		return descriptor;
	}
	
	/**
	 * To format the description output of the command.
	 * 
	 * @return the help description of the command
	 */
	public String formatOutput() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.commandExpression + "\n");
		
		for(int i = 0, strLength = this.commandDescription.length(); 
			i < strLength; i += 70) {
			buffer.append("\t");
			if(strLength - i > 70) {
				buffer.append(this.commandDescription.substring(i, i + 70));
				buffer.append("\n");
			}
			else {
				buffer.append(this.commandDescription.substring(i, strLength));
			}
		}
		
		return buffer.toString();
	}
}
