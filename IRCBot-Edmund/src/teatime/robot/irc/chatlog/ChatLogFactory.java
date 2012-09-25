package teatime.robot.irc.chatlog;

import com.teatime.common.util.LogUtil;

/**
 * The factory for creating ChatLog instances.
 * 
 * @author terryoy
 *
 */
public class ChatLogFactory {
	
	public static final String SIMPLE_CHAT_LOGGER = "SimpleChatLogger";
	public static final String SIMPLE_SEPARATE_CHAT_LOGGER = "SimpleSeparateChatLogger";

	public static ChatLog getChatLog(String logName, String logBaseName) {
		
		if(SIMPLE_CHAT_LOGGER.equals(logName)) {
			return new SimpleChatLogger(logBaseName);
		}
		else if(SIMPLE_SEPARATE_CHAT_LOGGER.equals(logName)) {
			return new SimpleSeparateChatLogger(logBaseName);
		}
		else {
			LogUtil.info("Chat logger not specified. Use default: " + SIMPLE_CHAT_LOGGER);
			return new SimpleChatLogger(logBaseName);
		}
		
	}
}
