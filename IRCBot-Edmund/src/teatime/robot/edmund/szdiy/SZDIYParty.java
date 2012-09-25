package teatime.robot.edmund.szdiy;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.teatime.common.util.DateTimeUtil;
import com.teatime.common.util.LogUtil;
import com.teatime.common.util.StringUtil;
import com.teatime.net.util.HTTPUtil;

public class SZDIYParty {
	

	private static final String URL_SZDIY_PARTY = "http://www.szdiy.org/party/{yyyy}/{yyyyMMdd}.txt";
	
	
	public String getPartyInfo() {
		if(isPartyTime(new Date())) {
			return getPartyAttendeesToday();
		}
		else {
			return "There is SZDIY party every Thursday night! 7:00 pm - 10:00 pm!";
		}
	}
	
	private boolean isPartyTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
		if(Calendar.THURSDAY == cal.get(Calendar.DAY_OF_WEEK)
				&& hourOfDay >= 19
				&& hourOfDay < 24) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getPartyAttendeesToday() {
		String msg = "Sorry, there is no party tonight! We all went out hacking!"; // default msg

		DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		Date now = new Date();
		String nameListUrl = URL_SZDIY_PARTY.replace("{yyyy}", Integer.toString(now.getYear() + 1900))
				.replace("{yyyyMMdd}", fmt.format(now));
		LogUtil.debug("Getting SZDIY event url:" + nameListUrl);
		
		// fetching name list		
		String result = "Today's attending SZDIYers are:\n" + HTTPUtil.downloadHTMLContent(nameListUrl);
		
		if(StringUtil.isEmpty(result))
			return msg;
		else
			return result;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		DateFormat fmt = DateTimeUtil.getDateFormat("yyyyMMdd HH:mm");
		SZDIYParty p = new SZDIYParty();
		System.out.println(p.isPartyTime(fmt.parse("20111206 10:00")));
		System.out.println(p.isPartyTime(fmt.parse("20111208 18:30")));
		System.out.println(p.isPartyTime(fmt.parse("20111208 19:30")));
		System.out.println(p.isPartyTime(fmt.parse("20111208 22:00")));
		System.out.println(p.isPartyTime(fmt.parse("20111208 23:03")));
		System.out.println(p.isPartyTime(fmt.parse("20111209 22:03")));
	}

}
