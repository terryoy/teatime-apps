package teatime.robot.irc;

public interface IntelligenceInterface {
	
	/**
	 * Reaction to people's words in public channel.
	 * 
	 * @param channel
	 * @param people
	 * @param message
	 * @return
	 */
	public void onPublicTalk(String channel, String people, String message);
	
	/**
	 * Reaction to people's words in private channel
	 * 
	 * @param people
	 * @param message
	 * @return
	 */
	public void onPrivateTalk(String people, String message);
	
	/**
	 * Send message to some people in some channel. 
	 * 
	 * @param channel channel name; if you want to send in private, the channel should be the people's name
	 * @param people not null if you want to emphasize the people's name (e.g. "terryoy: hello!")
	 * @param message
	 */
	public void sendMessage(String channel, String people, String message);
	
	/**
	 * Send message to some people in some channel. A shorter version.
	 * 
	 * @param channel channel name; if you want to send in private, the channel should be the people's name
	 * @param message
	 */
	public void sendMessage(String channel, String message);
}
