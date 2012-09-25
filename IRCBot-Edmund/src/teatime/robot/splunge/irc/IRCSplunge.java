package teatime.robot.splunge.irc;

import java.util.Date;

import teatime.robot.irc.AbstractIRCRobot;
import teatime.robot.irc.IRCRobot;
import teatime.robot.irc.IRCServer;
import teatime.robot.irc.chatlog.ChatLog;
import teatime.robot.irc.chatlog.ChatLogFactory;

import com.teatime.common.util.LogUtil;
import com.teatime.common.util.StringUtil;
import com.teatime.infra.util.ApplicationConfig;

/**
 * Splunge is the initial version of my testing IRC bot. It means it's a good 
 * idea but possibly not, and I'm not being indecisive.
 * 
 * @author terryoy
 *
 */
public class IRCSplunge extends AbstractIRCRobot implements IRCRobot {
	
	private static final String logName = "splunge";
	private static final String nickname = "Splunge{TerryOY}";
	private static final String EXIT_KEY = "ircbot.splunge.exitkey";
	private static final String CHAT_LOGGER_KEY = "ircbot.splunge.chatlog";
	private static final String VERBOSE_KEY = "ircbot.splunge.verbose";
	private static final String INTRO_TEXT_KEY = "ircbot.splunge.intro";
	
	
	private boolean exitFlag = false;
	private long reconnectDelay = 10000L; // 10s
	
	public IRCSplunge() {
		setName(nickname);
		ChatLog chatLog = ChatLogFactory.getChatLog(
				ApplicationConfig.getStringProp(CHAT_LOGGER_KEY), logName);
		this.setChatLog(chatLog);
	}
	
	@Override
	public void onMessage(String channel, String sender, String login,
			String hostname, String message) {
		// log message
		this.getChatLog().logChannelMsg(channel, sender, message);
		
		// response to message (remember to log your response too!)
		if(message.startsWith(nickname)) {
			if(message.endsWith("time")) {
				String time = new Date().toString();
				String reply = sender + ":" + time;
				
				this.sendBotMsg(channel, reply);
			}
			else {
				String introMsg = ApplicationConfig.getStringProp(INTRO_TEXT_KEY);
				this.sendBotMsg(channel, introMsg);
			}
		}
		
	}
	
	@Override
	protected void onPrivateMessage(String sender, String login, 
			String hostname, String message) {
		
		// log message
		this.getChatLog().logPrivateMsg(sender, message);
		
		// response to message (remember to log your response too!)
		if(message.equalsIgnoreCase("time")) {
			String time = new Date().toString();
			String reply = sender + ":" + time;
			
			this.sendBotMsg(sender, reply);
		}
		else if(message.equalsIgnoreCase(
				ApplicationConfig.getStringProp(EXIT_KEY))) {
			this.sendBotMsg(sender, "Understood. I'm exiting...");
			
			// exit program
			this.exit();
		}
		else if(message.equalsIgnoreCase("test disconnect")) {
			this.disconnect();
		}
		else if(message.indexOf("long") > 0) {
			this.sendBotMsg(sender, 
					"This is a very long message: Figure 3 shows the above structure using UML's Object Constraint Language (OCL) to express constraints on the state class. The feature-state constraint expression uses the variable \"this\" to mean the object to which the state is attached. UML is also extended here to distinguish feature-state constraints with a stereotyped constraint note, because the semantics of ordinary constraint notes on a state is ambiguous (see last section). A feature-state constraint is required to be true for the entire time that the object is in the state. The line between the SUSAN-MARRIED 1 state instance and the marriage link between SUSAN and JOHN expresses the justification for Susan's married state, namely the marriage link between her and John. This is also a UML extension, because UML would normally require a link object interposed between the link and any associations to it (the notation of the figure means the same thing, but is more compact).");
		}
		else {
			this.sendBotMsg(sender, "Thanks! Your message is received.");
		}
		
	}

	protected void exit() {
		// set exit flag true
		this.exitFlag = true;
		
		// close the log streams
		this.getChatLog().closeLogger();
		
		// sleep a bit before exiting.
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.disconnect();

		// clean up
		LogUtil.error("Oh no, your program didn't exit as expected. Force exit");
		System.exit(0);
	}

	
	@Override
	protected void onDisconnect() {
		// if it's not exiting, try to reconnect server
		if(!this.exitFlag) {
			
			LogUtil.info("Unexpectedly disconnected. Trying to reconnect.");
			
			while (!isConnected()) {
			    try {
			        reconnect();
					joinChannels(IRCServer.CHNL_TEST_CHANNEL);
			    }
			    catch (Exception e) {
			        // Couldn't reconnect!
			        // Pause for a short while...?
			    	
			    	LogUtil.error("Cannot reconnect, wait " + 
			    			(reconnectDelay/1000) + " seconds...");
			    	try {
						Thread.sleep(reconnectDelay);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
			    }
			}
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IRCSplunge bot = new IRCSplunge();
		bot.setVerbose(
				ApplicationConfig.getBooleanProp(VERBOSE_KEY));
		
		// connect to server
		try {
			bot.connect(IRCServer.FREENODE);
		} catch (Exception e) {
			LogUtil.error("Can't connect: " + e);
		}
		
		// register my self

		// join channels
		bot.joinChannels(IRCServer.CHNL_TEST_CHANNEL);
		
//		bot.joinChannel("#AnotherTest");

		// start listening

		// quit channels

		// quit server

		// quit my self
	}
}
