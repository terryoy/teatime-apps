package teatime.robot.irc.chatlog;

import java.io.PrintWriter;
import java.util.Date;

import com.teatime.common.util.IOUtil;
import com.teatime.common.util.LogUtil;

public class SimpleSeparateChatLogger extends SimpleChatLogger {
	
	private PrintWriter pmLog;
	private PrintWriter chnLog; 

	public SimpleSeparateChatLogger(String logBaseName) {
		super(logBaseName);
	}

	@Override
	protected void createNewDateLogger() {

		String today = this.dayFmt.format(new Date());
		
		if(today.equals(this.logCreateDate)) {
			LogUtil.error("Wrong create new log call: " 
					+ today + "[" + logBaseName + "]");
			return;
		}
		
		// create a channel log 
		if(chnLog != null) {
			chnLog.flush();
			chnLog.close();
		}
		chnLog = IOUtil.getFilePrintWriter(getBasePath() +
				logBaseName + "_chn_" + today + ".log", true);
		
		// create a channel log 
		if(pmLog != null) {
			pmLog.flush();
			pmLog.close();
		}
		pmLog = IOUtil.getFilePrintWriter(getBasePath() +
				logBaseName + "_pm_" + today + ".log", true);
		
		this.logCreateDate = today; 
		
		LogUtil.debug("chnLog and pmLog are created for " + today);
	}

	@Override
	protected PrintWriter getChannelLogger() {
		return this.chnLog;
	}

	@Override
	protected PrintWriter getPrivateMsgLogger() {
		return this.pmLog;
	}

	@Override
	protected void checkLog(Date now) {
		if(!dayFmt.format(now).equals(this.logCreateDate))
			this.createNewDateLogger();
		
		if(cnt > LOG_FLUSH_LIMIT) {
			this.chnLog.flush();
			cnt = 0;
		}
		else
			cnt++;
	}

	@Override
	public void flushLogger() {
		this.chnLog.flush();
		this.pmLog.flush();
	}

	@Override
	public void closeLogger() {
		this.chnLog.flush();
		this.chnLog.close();
		this.pmLog.flush();
		this.pmLog.close();

		LogUtil.debug("chatlog close");
	}
	
}
