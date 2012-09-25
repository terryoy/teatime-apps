package teatime.robot.edmund.monitor;

import teatime.robot.edmund.iengine.AbstractChatModule;
import teatime.robot.edmund.iengine.ChatModule;
import teatime.robot.edmund.iengine.IntelligenceEdmund;

public class MonitorChatModule extends AbstractChatModule implements ChatModule {

	public MonitorChatModule(IntelligenceEdmund brain) {
		super(brain);
	}

	@Override
	public boolean replyMessage(String channel, String people, String message) {
		// TODO Auto-generated method stub
		return false;
	}

}
