package teatime.robot.irc;

import org.jibble.pircbot.PircBot;

import teatime.robot.irc.chatlog.ChatLog;

import com.teatime.common.util.LogUtil;
import com.teatime.common.util.StringUtil;
import com.teatime.infra.util.ApplicationConfig;

public abstract class AbstractIRCRobot extends PircBot implements IRCRobot {

	private ChatLog chatLog;
	
	private static final String MSG_SIZE_LIMIT_KEY = "ircbot.base.msgsize_limit";
	
	/**
	 * Use a override send message to log reply message too.
	 * 
	 * @param target
	 * @param message
	 */
	public void sendBotMsg(String channel, String person, String msg) {
		String finalMsg = (person == null) ? msg : person + ": " + msg; 
		
		int msgSizeLimit = ApplicationConfig.getIntegerProp(MSG_SIZE_LIMIT_KEY);

		int start_pos = 0;
		int end_pos = msgSizeLimit > finalMsg.length() ? finalMsg.length() : start_pos + msgSizeLimit;
		do {
			this.sendSingleMsg(channel, finalMsg.substring(start_pos, end_pos));
			start_pos += msgSizeLimit;
			end_pos = (end_pos + msgSizeLimit) >= finalMsg.length() ? finalMsg.length() : start_pos + msgSizeLimit;
		} while (start_pos < end_pos);
		
	}
	
	/**
	 * Use a override send message to log reply message too.
	 * 
	 * @param target
	 * @param message
	 */
	public void sendBotMsg(String channel, String message) {
		this.sendBotMsg(channel, null, message);
	}
	
	/**
	 * It is a method for sending a single message with in the size limit, the 
	 * public method(sendBotMsg) to send message should be automatically split
	 * the message into segments within the size limit.  
	 * 
	 * @param target
	 * @param message
	 */
	private void sendSingleMsg(String channel, String message) {
		super.sendMessage(channel, message);

		if(StringUtil.isEmpty(channel)) {
			return;
		}
		if(channel.startsWith("#") || channel.startsWith("&")) {
			this.chatLog.logChannelMsg(channel, this.getName(), message);
		}
		else {
			this.chatLog.logPrivateMsg("to " + channel, message);
		}
	}
	
	public void setChatLog(ChatLog logger) {
		this.chatLog = logger;
	}
	
	public ChatLog getChatLog() {
		return this.chatLog;
	}

	/**
	 * Join a list of channels separated by ","(e.g."#szdiy,#terryoytest").
	 * 
	 * @param channels
	 */
	public void joinChannels(String channels) {
		
		try {
			String[] chnList = channels.split(",");
			for(String chn: chnList) {
				this.joinChannel(chn);
			}
				
		} catch(Exception e) {
			LogUtil.error("join channels error");
			e.printStackTrace();
		}
		
	}
	
}
