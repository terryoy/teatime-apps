package teatime.robot.edmund.irc;

import teatime.robot.edmund.iengine.IntelligenceEdmund;
import teatime.robot.irc.IntelligenceInterface;

import com.teatime.infra.util.ApplicationConfig;

public class EdmundIRCInterface implements IntelligenceInterface {
	
	private IRCEdmund edmund;
	private IntelligenceEdmund iEdmund;

	public EdmundIRCInterface(IRCEdmund bot) {
		this.edmund = bot;
		this.iEdmund = new IntelligenceEdmund(this);
	}

	@Override
	public String getNickName() {
		// TODO Auto-generated method stub
		return this.edmund.getNick();
	}
	
	public void onPrivateTalk(String people, String message) {
		
		// if it's an exit command,
		if(message.equalsIgnoreCase(
				ApplicationConfig.getStringProp(IRCEdmund.EXIT_KEY))) {
			edmund.sendBotMsg(people, "Understood. I'm exiting...");

			// exit program
//			edmund.exit(); // drop the exit function, no exit by irc calling
			edmund.sendBotMsg(people, "Nahh, just kidding! haha");
		}
		
		// handle other private messages
		else {
			iEdmund.answerTo(IntelligenceEdmund.CHANNEL_PRIVATE, people, message); 
		}
	}

	public void onPublicTalk(String channel, String people, String message) {
		// handle public messages
		iEdmund.answerTo(channel, people, message);
	}
	
	@Override
	public void sendMessage(String channel, String message) {
		edmund.sendBotMsg(channel, message);
	}

	@Override
	public void sendMessage(String channel, String people, String message) {
		// TODO Auto-generated method stub
		edmund.sendBotMsg(channel, people, message);
	}

}
