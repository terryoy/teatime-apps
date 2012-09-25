package teatime.robot.edmund.iengine;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import teatime.robot.edmund.autojob.AutoJobModule;
import teatime.robot.edmund.language.LanguageModule;
import teatime.robot.edmund.library.WikiLibrary;
import teatime.robot.edmund.monitor.MonitorChatModule;
import teatime.robot.edmund.szdiy.SZDIYFun;
import teatime.robot.edmund.utilities.PersonalUtilities;
import teatime.robot.irc.IntelligenceInterface;

/**
 * For thinking and interacting of Edmund. An integration facade of all the 
 * functions of Edmund's brain.
 * 
 * @author terryoy
 *
 */
public class IntelligenceEdmund {

	public static String CHANNEL_PRIVATE = "private";
	private String myName = "Edmund";
	
	private MonitorChatModule monitor;
	private PersonalUtilities personalUtil;
	private AutoJobModule jobEngine;
	private LanguageModule langEngine;
	private WikiLibrary wikiLibrary;
	private SZDIYFun szdiy;
	
	private Collection<ChatModule> moduleList;
	private IntelligenceInterface chatInterface;
	
	public IntelligenceEdmund(IntelligenceInterface chatInterface) {
		this.chatInterface = chatInterface;
		
		this.monitor = new MonitorChatModule(this);
		this.personalUtil = new PersonalUtilities(this);
		this.jobEngine = new AutoJobModule(this);
		this.langEngine = new LanguageModule(this);
		this.wikiLibrary = new WikiLibrary(this);
		this.szdiy = new SZDIYFun(this);

		this.moduleList = new LinkedList<ChatModule>();
		
		register(this.monitor);
		register(this.personalUtil);
		register(this.jobEngine);
		register(this.wikiLibrary);
		register(this.szdiy);
		register(this.langEngine);
		
	}

	private void register(ChatModule module) {
		this.moduleList.add(module);
	}

	/**
	 * Overall method to answer a person's message in a public/private channel.
	 * 
	 * @param channel
	 * @param people
	 * @param message
	 * @return
	 */
	public void answerTo(String channel, String people, String message) {
		Iterator<ChatModule> modItr = this.getModuleIterator();
		
		while(modItr.hasNext()) {
			ChatModule module = modItr.next();
			if(module.replyMessage(channel, people, message))
				return;
		}
		
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}
	
	public Iterator<ChatModule> getModuleIterator() {
		return this.moduleList.iterator();
	}
	
	public IntelligenceInterface getChatInterface() {
		return this.chatInterface;
	}
}
