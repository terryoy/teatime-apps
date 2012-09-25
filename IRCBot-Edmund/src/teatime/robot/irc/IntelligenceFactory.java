package teatime.robot.irc;

import teatime.robot.edmund.irc.EdmundIRCInterface;
import teatime.robot.edmund.irc.IRCEdmund;

public class IntelligenceFactory {

	public static IntelligenceInterface getIntelligence(IRCEdmund bot, String name) {
		if("irc".equals(name)) {
			return new EdmundIRCInterface(bot);
		}
		else {
			// ... to be changed
			return new EdmundIRCInterface(bot);
		}
	}
	
}
