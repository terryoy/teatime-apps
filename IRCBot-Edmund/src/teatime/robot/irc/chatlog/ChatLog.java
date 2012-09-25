package teatime.robot.irc.chatlog;



/**
 * Interface of the Chat Logger.
 * 
 * @author terryoy
 *
 */
public interface ChatLog {

	public void logPrivateMsg(String sender, String message);
	
	public void logChannelMsg(String channel, String sender, String message);
	
	public void closeLogger();
	
}
