package teatime.robot.irc.chatlog;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import com.teatime.common.util.DateTimeUtil;
import com.teatime.common.util.FileUtil;
import com.teatime.common.util.IOUtil;
import com.teatime.common.util.LogUtil;
import com.teatime.infra.util.ApplicationConfig;

public class SimpleChatLogger implements ChatLog {

	protected static final String delimiter = " "; 
	
	protected String logBasePath = ApplicationConfig.getStringProp("ircbot.base.chatlogpath");
	protected String logBaseName;
	protected String logCreateDate;
	private PrintWriter chatLog; // channel log
	
	protected DateFormat dayFmt;
	protected DateFormat timeFmt;

	protected static final int LOG_FLUSH_LIMIT = 10;
	protected int cnt = 0;
	
	public SimpleChatLogger(String logBaseName) {
		this.logBaseName = logBaseName;
		this.dayFmt = DateTimeUtil.getDateFormat("yyyyMMdd");
		this.timeFmt = DateTimeUtil.getDateFormat("HH:mm:ss");
		this.createNewDateLogger();
	}

	public void logChannelMsg(String channel, String sender, String message) {
		Date now = new Date();
		checkLog(now);
		
		StringBuffer buf = new StringBuffer();
		buf.append(channel);
		buf.append(delimiter);
		buf.append(timeFmt.format(now));
		buf.append(delimiter);
		buf.append("["+sender+"]:");
		buf.append(delimiter);
		buf.append(message);
		
		getChannelLogger().println(buf.toString());
		getChannelLogger().flush();
		LogUtil.debug("chn msg: " + buf.toString());
	}


	public void logPrivateMsg(String sender, String message) {
		Date now = new Date();
		checkLog(now);
		
		StringBuffer buf = new StringBuffer();
		buf.append("[PRIVATE]");
		buf.append(delimiter);
		buf.append(timeFmt.format(now));
		buf.append(delimiter);
		buf.append("["+sender+"]:");
		buf.append(delimiter);
		buf.append(message);
		
		getPrivateMsgLogger().println(buf.toString());
		getPrivateMsgLogger().flush();
		LogUtil.debug("pvt msg: " + buf.toString());
	}

	protected void createNewDateLogger() {
		String today = this.dayFmt.format(new Date());
		
		if(today.equals(this.logCreateDate)) {
			LogUtil.error("Wrong create new log call: " 
					+ today + "[" + logBaseName + "]");
			return;
		}
		
		// create a channel log 
		if(chatLog != null) {
			chatLog.flush();
			chatLog.close();
		}
		chatLog = IOUtil.getFilePrintWriter(getBasePath() +
				logBaseName + "_all_" + today + ".log", true);
		
		this.logCreateDate = today; 
		
		LogUtil.debug("chatLog created: " + chatLog);
	}

	protected PrintWriter getChannelLogger() {
		return this.chatLog;
	}
	
	protected PrintWriter getPrivateMsgLogger() {
		return this.chatLog;
	}
	
	protected String getBasePath() {
		String basePath = this.logBasePath 
			+ (this.logBasePath.endsWith("/") ? "" : "/") // auto-append a slash
			+ this.logBaseName;
		
		if(!FileUtil.fileExists(basePath))
			FileUtil.createDirs(basePath);
		
		return basePath + "/";
	}

	protected void checkLog(Date now) {
		if(!dayFmt.format(now).equals(this.logCreateDate))
			this.createNewDateLogger();
		
		if(cnt > LOG_FLUSH_LIMIT) {
			this.chatLog.flush();
			cnt = 0;
		}
		else
			cnt++;
	}
	
	public void flushLogger() {
		this.chatLog.flush();
	}

	public void closeLogger() {
		this.chatLog.flush();
		this.chatLog.close();

		LogUtil.debug("chatlog close");
	}

}
