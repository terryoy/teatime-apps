package teatime.robot.edmund.utilities;

import teatime.robot.edmund.iengine.AbstractChatModule;
import teatime.robot.edmund.iengine.ChatModule;
import teatime.robot.edmund.iengine.IntelligenceEdmund;

public class PersonalUtilities extends AbstractChatModule implements ChatModule {

	public PersonalUtilities(IntelligenceEdmund brain) {
		super(brain);
	}

	@Override
	public boolean replyMessage(String channel, String people, String message) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
