package teatime.robot.edmund.irc;

import teatime.robot.irc.AbstractIRCRobot;
import teatime.robot.irc.IRCRobot;
import teatime.robot.irc.IRCServer;
import teatime.robot.irc.IntelligenceFactory;
import teatime.robot.irc.IntelligenceInterface;
import teatime.robot.irc.chatlog.ChatLog;
import teatime.robot.irc.chatlog.ChatLogFactory;

import com.teatime.common.util.LogUtil;
import com.teatime.common.util.StringUtil;
import com.teatime.infra.util.ApplicationConfig;

/**
 * Edmund is a IRC messenger robot.
 * 
 * @author terryoy
 *
 */
public class IRCEdmund extends AbstractIRCRobot implements IRCRobot {

	private static final String DEFAULT_NICK = "Edmund{TerryOY}";
	private static final String logName = "edmund";
	
	public static final String REG_NICK_KEY = "ircbot.edmund.reg_nick";
	public static final String REG_PASSWORD_KEY = "ircbot.edmund.password";
	public static final String VERBOSE_KEY = "ircbot.edmund.verbose";
	public static final String CHAT_LOGGER_KEY = "ircbot.edmund.chatlog";
	public static final String CHANNELS_KEY = "ircbot.edmund.irc_channels"; // auto-join channels
	public static final String EXIT_KEY = "ircbot.edmund.exitkey"; // string to call robot exit
	
	private boolean exitFlag = false;
	private static final long reconnectDelay = 60000L; // 60s
	
	private IntelligenceInterface iEdmund;

	public IRCEdmund() {
		setName(DEFAULT_NICK);
		ChatLog chatLog = ChatLogFactory.getChatLog(
				ApplicationConfig.getStringProp(CHAT_LOGGER_KEY), logName);
		this.setChatLog(chatLog);
		
		this.iEdmund = IntelligenceFactory.getIntelligence(this, "irc");
	}
	
	/**
	 * Main method of the IRCEdmund application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		IRCEdmund edmund = new IRCEdmund();
		edmund.setVerbose(
				ApplicationConfig.getBooleanProp(VERBOSE_KEY));
		
		// load nick name and pasword
		String nickName = ApplicationConfig.getStringProp(REG_NICK_KEY);
		edmund.setName(nickName);
		
		// connect to server
		try {
			edmund.connect(IRCServer.FREENODE);
		} catch (Exception e) {
			LogUtil.error("Can't connect: " + e);
		}

		// register to nickname with password
		if(!StringUtil.isEmpty(nickName)){
			edmund.identify(
				ApplicationConfig.getStringProp(REG_PASSWORD_KEY));
		}
		
		// join channels
		edmund.joinChannels(
				ApplicationConfig.getStringProp(CHANNELS_KEY));
		
		// running in loop now
		
	}

	@Override
	protected void onMessage(String channel, String sender, String login, String hostname, String message) {
		LogUtil.debug("channel msg: " + channel + "|" + sender + "|" + login + "|" + hostname + "|" + message);
		
		this.getChatLog().logChannelMsg(channel, sender, message);
		
		// give to EdmundIntelligence to handle
		this.iEdmund.onPublicTalk(channel, sender, message);
	}

	@Override
	protected void onPrivateMessage(String sender, String login, String hostname, String message) {
		LogUtil.debug("channel msg: " + sender + "|" + login + "|" + hostname + "|" + message);
		
		this.getChatLog().logPrivateMsg(sender, message);

		// give to EdmundIntelligence to handle
		this.iEdmund.onPrivateTalk(sender, message);
		
	}

	public void exit() {
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
		LogUtil.info("call system exit(0); program exit");
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
			        joinChannels(
			        		ApplicationConfig.getStringProp(CHANNELS_KEY));
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
	
	
}
