package teatime.robot.edmund.library;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.teatime.common.util.FileUtil;

public class SimpleWikiTextExtractor {

	private static Pattern revPtn = Pattern.compile("<rev xml:space=\"preserve\">([^<]*)</rev>", Pattern.MULTILINE);
	
	public static String getPlainTextFromWikiXml(String wikiXml) {
		Matcher m = revPtn.matcher(wikiXml);
		String result = "";
		if(m.find()) {
			result = m.group(1);
//			LogUtil.debug("Once match:" + result);
//			LogUtil.debug("---------------------------------------------");
			
			// remove the info boxes
			StringBuffer buf = new StringBuffer();
			int stackCnt = 0;
			for(int i = 0; i < result.length(); i++) {
				char c = result.charAt(i);
				if(c == '{') 
					stackCnt++;
				else if (c == '}')
					stackCnt--;
				else if(stackCnt <= 0)
					buf.append(c);
			}
			
			result = buf.toString();
			
			// remove ref
			result = result.replaceAll("&lt;ref&gt;.*&lt;/ref&gt;", "");
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String content = FileUtil.readFileAsString("resources/test/wikitext.txt");
//		System.out.println(content);
		
		System.out.println(getPlainTextFromWikiXml(content));
	}
}
