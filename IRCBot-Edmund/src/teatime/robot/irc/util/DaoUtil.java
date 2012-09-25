package teatime.robot.irc.util;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DaoUtil {

	private static final String databaseUrl = "jdbc:h2:mem:irc_edmund";
	private static ConnectionSource memConnSource;
	private static DaoUtil instance;
	
	private DaoUtil()  {
		try {
			memConnSource = new JdbcConnectionSource(databaseUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionSource getMemConnectionSource() {
		if(instance == null)
			instance = new DaoUtil();
		
		return memConnSource;
	}
}
