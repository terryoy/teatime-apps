/**
 * 
 */
package com.teatime.memo.console;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.teatime.memo.service.exception.ResourceAccessException;
import com.teatime.memo.service.fileimpl.MemoEntryServiceFileImpl;

/**
 * @author Terry Ouyang
 *
 */
public class MemoConsole {
	
	private File baseDir = null;
	
	private MemoCommandProcessor controller = null;
	
	public void setPath(String path) {
		this.baseDir = new File(path);
	}

	public void run() {
		
		// initialize service
		initialize();
	
		if(controller == null) {
			System.out.println("System initiliazation error!");
			return;
		}
		
		// print software information
		printSoftwareInfo();
		
		// start loop
		BufferedReader in = new BufferedReader(
				new InputStreamReader(System.in));
		while(true) {
			System.out.print("teatime>");
			String line;
			try {
				line = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			
			if(controller.executeCommand(line))
				break;
		}
	}
	
	/**
	 * 
	 */
	private void initialize() {
		// set baseDir
		if(baseDir == null) {
			baseDir = new File(".");
		}

		// initialize MemoController
		try {
			this.controller = new MemoCommandProcessor(
					new MemoEntryServiceFileImpl(baseDir));
		} catch (ResourceAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void printSoftwareInfo() {
		System.out.println("TeaTime Memo v0.1 - write your daily memo easily.");
		System.out.println("Written by Terry Ouyang (2009)");
		System.out.println();
		controller.executeCommand("help");
	}

	public static void main(String[] args) {
		MemoConsole console = new MemoConsole();
		
		console.setPath(".");
		console.run();
		
	}
}
