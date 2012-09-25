package teatime.robot.edmund.language;

import java.io.FileNotFoundException;

import teatime.robot.edmund.iengine.AbstractChatModule;
import teatime.robot.edmund.iengine.ChatModule;
import teatime.robot.edmund.iengine.IntelligenceEdmund;

public class LanguageModule extends AbstractChatModule implements ChatModule {
	
	public TrivialCorpus corpus;
	
	public LanguageModule(IntelligenceEdmund brain) {
		super(brain);
		
		// init corpus
		this.corpus = new TrivialCorpus();
		try {
			this.corpus.init("resources/edmund/trivial_corpus.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String pickupLine(String person) {
		String line = this.corpus.getRandomLine(); 
		String me = this.getEdmund().getMyName();
		String you = person;
		return line.replace("{0}", me).replace("{1}", you);
	}

	@Override
	public boolean replyMessage(String channel, String person, String message) {
		// if the speaking target is to Edmund, it is true 
		if(message.contains(getEdmund().getMyName()) || IntelligenceEdmund.CHANNEL_PRIVATE.equals(channel)) {
			String line = pickupLine(person);
			this.getEdmund().getChatInterface().sendMessage(channel, line);
			return true;
		}
		
		return false;
	}

}
