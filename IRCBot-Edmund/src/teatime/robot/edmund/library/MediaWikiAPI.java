package teatime.robot.edmund.library;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.teatime.common.util.LogUtil;
import com.teatime.net.util.HTTPHeaders;
import com.teatime.net.util.HTTPUtil;
import com.teatime.net.util.WebAPIUtil;

/**
 * MediaWiki API Wrapper.
 * 
 *
 * Reference from MediaWiki:
 * http://zh.wikipedia.org/w/api.php
 * 
 * Parameters:
 * 	  format              - The format of the output
 * 	                        One value: json, jsonfm, php, phpfm, wddx, wddxfm, xml, xmlfm, yaml, yamlfm, rawfm, txt, txtfm, dbg, dbgfm,
 * 	                            dump, dumpfm
 * 	                        Default: xmlfm
 * 	  action              - What action you would like to perform. See below for module help
 * 	                        One value: sitematrix, titleblacklist, opensearch, userdailycontribs, clicktracking, specialclicktracking,
 * 	                            articlefeedback, wikilove, wikiloveimagelog, login, logout, query, expandtemplates, parse,
 * 	                            feedcontributions, feedwatchlist, help, paraminfo, rsd, compare, purge, rollback, delete, undelete,
 * 	                            protect, block, unblock, move, edit, upload, filerevert, emailuser, watch, patrol, import,
 * 	                            userrights
 * 	                        Default: help
 * 	  version             - When showing help, include version for each module
 * 	  maxlag              - Maximum lag can be used when MediaWiki is installed on a database replicated cluster.
 * 	                        To save actions causing any more site replication lag, this parameter can make the client
 * 	                        wait until the replication lag is less than the specified value.
 * 	                        In case of a replag error, a HTTP 503 error is returned, with the message like
 * 	                        "Waiting for $host: $lag seconds lagged\n".
 * 	                        See http://www.mediawiki.org/wiki/Manual:Maxlag_parameter for more information
 * 	  smaxage             - Set the s-maxage header to this many seconds. Errors are never cached
 * 	                        Default: 0
 * 	  maxage              - Set the max-age header to this many seconds. Errors are never cached
 * 	                        Default: 0
 * 	  requestid           - Request ID to distinguish requests. This will just be output back to you
 * 	  servedby            - Include the hostname that served the request in the results. Unconditionally shown on error
 *  
 * 
 * 
 * @author terryoy
 *
 */
public class MediaWikiAPI {
	
	private static final String URL_WIKI_EN = "http://en.wikipedia.org/w/api.php";
	private static final String URL_WIKI_ZH = "http://zh.wikipedia.org/w/api.php";
	private static final String URL_WIKIMEDIA_COMMONS = "http://commons.wikimedia.org/w/api.php";
	private static final String URL_WIKIONARY = "http://en.wiktionary.org/w/api.php";
	private static final String URL_WIKIBOOKS = "http://en.wikibooks.org/w/api.php";
	private static final String URL_WIKISOURCE = "http://en.wikisource.org/w/api.php";
	
	private static final String API_USER_AGENT = "EdmundRobot/0.1 (+terryoy@163.com)";

	private static final String PARAM_FORMAT = "format";
	private static final String PARAM_ACTION = "action";
	
	private static final String FORMAT_JSON = "json";
	private static final String FORMAT_TXT = "txt";
	private static final String FORMAT_XML = "xml";
	private static final String FORMAT_YAML = "yaml";
	private static final String ACTION_QUERY = "query";
	
	private static final String QUERY_TITLES = "titles";
	private static final String QUERY_PROPS = "prop";
	private static final String QUERY_RVPROPS = "rvprop";
	
	private String getResponseFromAPI(String apiUrl, Map<String, String> paramMap) {
		String requestUrl = apiUrl + WebAPIUtil.getParameterString(paramMap);
		
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put(HTTPHeaders.USER_AGENT, API_USER_AGENT);
		LogUtil.debug("request URL:" + requestUrl);
		return HTTPUtil.downloadHTMLContent(requestUrl, null, headerMap);
	}
	
	public String searchWikipediaEn(String keyword) {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		paramMap.put(PARAM_ACTION, ACTION_QUERY);
		paramMap.put(PARAM_FORMAT, FORMAT_XML);
		paramMap.put(QUERY_PROPS, "revisions");
		paramMap.put(QUERY_RVPROPS, "content");
		paramMap.put("rvsection", "0");
		paramMap.put(QUERY_TITLES, keyword);
		
		
		String result = "";
		result = this.getResponseFromAPI(URL_WIKI_EN, paramMap);
		result = SimpleWikiTextExtractor.getPlainTextFromWikiXml(result);
		result = result.trim();
		
		if(result.startsWith("#REDIRECT")) {
			String retry = result.substring(
					result.indexOf("[[") + 2, result.indexOf("]]"));

			LogUtil.debug("redirect to: " + retry);
			result = this.searchWikipediaEn(retry);
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		MediaWikiAPI api = new MediaWikiAPI();
		String searchStr = api.searchWikipediaEn("Monty Python");
		System.out.println("Wiki search result:");
		System.out.println(searchStr);
		
		System.out.println("--------------------------------------------");
		
		String searchStr1 = api.searchWikipediaEn("Artificial Intelligence");
		System.out.println("Wiki search result:");
		System.out.println(searchStr1);
		
	}
}
