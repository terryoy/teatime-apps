package teatime.robot.edmund.szdiy;

import teatime.robot.edmund.iengine.AbstractChatModule;
import teatime.robot.edmund.iengine.ChatModule;
import teatime.robot.edmund.iengine.IntelligenceEdmund;

public class SZDIYFun extends AbstractChatModule implements ChatModule {
	
	private SZDIYParty party = new SZDIYParty();
	private SZDIYRoll roll = new SZDIYRoll();

	public SZDIYFun(IntelligenceEdmund brain) {
		super(brain);
	}
	
	public String getSZDIYPartyInfo() {
		return this.party.getPartyInfo();
	}
	

	@Override
	public boolean replyMessage(String channel, String people, String message) {
		
		if (message.startsWith(this.getEdmund().getChatInterface().getNickName()) && message.toLowerCase().contains("roll")) {
			
			String reply = this.roll.rollLine(message);
			if (reply != null) {
				this.getEdmund().getChatInterface().sendMessage(channel, people, reply);
				return true;
			}
		}
		
		if(message.contains(getEdmund().getMyName()) || IntelligenceEdmund.CHANNEL_PRIVATE.equals(channel)) {
			String msgLowerCase = message.toLowerCase();
			
			// szdiy party info
			if(msgLowerCase.contains("szdiy")  
//					&& (msgLowerCase.contains("party") || msgLowerCase.contains("event") 
//							|| msgLowerCase.contains("tonight") || msgLowerCase.contains("thursday"))
							) {
				String reply = getSZDIYPartyInfo();
				this.getEdmund().getChatInterface().sendMessage(channel, reply);
				return true;
			}
		}
		
		
		return false;
	}
	
	public static void main(String[] args) {
		SZDIYFun szdiy = new SZDIYFun(null);
		
		System.out.println(szdiy.getSZDIYPartyInfo());
		
	}
}
