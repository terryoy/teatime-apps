package teatime.robot.edmund.autojob;

import teatime.robot.edmund.iengine.AbstractChatModule;
import teatime.robot.edmund.iengine.ChatModule;
import teatime.robot.edmund.iengine.IntelligenceEdmund;

public class AutoJobModule extends AbstractChatModule implements ChatModule {

	public AutoJobModule(IntelligenceEdmund iEdmund) {
		super(iEdmund);
	}

	@Override
	public boolean replyMessage(String channel, String people, String message) {
		// TODO Auto-generated method stub
		return false;
	}

}
