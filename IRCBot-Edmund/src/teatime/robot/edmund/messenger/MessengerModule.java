package teatime.robot.edmund.messenger;

import teatime.robot.edmund.iengine.AbstractChatModule;
import teatime.robot.edmund.iengine.IntelligenceEdmund;
import teatime.robot.edmund.messenger.model.Message;
import teatime.robot.irc.util.DaoUtil;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

public class MessengerModule extends AbstractChatModule {
	
	private Dao<Message, String> msgDao;
	
	public MessengerModule(IntelligenceEdmund iEdmund) {
		super(iEdmund);

		try {
			msgDao = DaoManager.createDao(DaoUtil.getMemConnectionSource(), 
					Message.class);
			// create tables
			TableUtils.createTable(DaoUtil.getMemConnectionSource(), Message.class);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
	}

	@Override
	public boolean replyMessage(String channel, String people, String message) {
		// TODO Auto-generated method stub
		return false;
	}

}
