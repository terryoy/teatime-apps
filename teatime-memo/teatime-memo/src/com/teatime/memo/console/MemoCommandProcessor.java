/**
 * 
 */
package com.teatime.memo.console;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.teatime.memo.console.command.ExitCommand;
import com.teatime.memo.console.command.HelpCommand;
import com.teatime.memo.console.command.QuitCommand;
import com.teatime.memo.console.command.RemoveMemoCommand;
import com.teatime.memo.console.command.ViewMemoCommand;
import com.teatime.memo.console.command.WriteMemoCommand;
import com.teatime.memo.service.MemoEntryService;

/**
 * @author Terry Ouyang
 *
 */
public class MemoCommandProcessor {

	MemoEntryService memoEntryService;
	Map<String, Command> commandMap = new HashMap<String, Command>();
	List<Command> commandList = new LinkedList<Command>();
	
	public MemoCommandProcessor(MemoEntryService memoEntryService) {
		this.memoEntryService = memoEntryService;
		
		initializeCommands();
	}
	
	private void initializeCommands() {
		this.addCommand(new WriteMemoCommand(this.memoEntryService));
		this.addCommand(new ViewMemoCommand(this.memoEntryService));
		this.addCommand(new RemoveMemoCommand(this.memoEntryService));
		this.addCommand(new HelpCommand(this));
		this.addCommand(new QuitCommand());
		this.addCommand(new ExitCommand());
	}

	public void addCommand(Command command) {
		this.commandList.add(command);
		this.commandMap.put(command.getIdentifier(), command);
	}
	
	public List<Command> getCommandList() {
		return this.commandList;
	}
	
	public Map<String, Command> getCommandMap() {
		return this.commandMap;
	}
	
	/**
	 * The return value denotes whether to terminate the
	 * loop or not.
	 * 
	 * @param commandLine
	 * @return
	 */
	public boolean executeCommand(String commandLine) {
		if(commandLine == null || 
				"".equals(commandLine.trim())) {
			// nothing input
			return false;
		}
		String[] args = commandLine.split(" ");
		// find command
		Command command = this.commandMap.get(args[0].toLowerCase());
		if(command == null) {
			System.out.println("unregconized command!");
			return false;
		}
		else
			// execute command
			return command.executeCommand(commandLine);
	}
}
