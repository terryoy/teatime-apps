package teatime.robot.edmund.library;

import teatime.robot.edmund.iengine.AbstractChatModule;
import teatime.robot.edmund.iengine.ChatModule;
import teatime.robot.edmund.iengine.IntelligenceEdmund;

public class WikiLibrary extends AbstractChatModule implements ChatModule {

	public WikiLibrary(IntelligenceEdmund brain) {
		super(brain);
	}

	public String lookupInDict(String string) {
		String result = "";
		
		return result;
	}	
	
	public String googleSearch(String keyword) {
		String result = "";
		
		return result;
	}
	
	public String wikipediaSearch(String keyword) {
		String result = "";
		
		return result;
	}
	
	public static void main(String[] args) {
		WikiLibrary lib = new WikiLibrary(null);
		
		System.out.println("Dict lookup thing: " +
				lib.lookupInDict("thing"));
		System.out.println("Google lookup thing:" +
				lib.googleSearch("thing"));
		
	}

	@Override
	public boolean replyMessage(String channel, String people, String message) {
		// TODO Auto-generated method stub
		return false;
	}

}
