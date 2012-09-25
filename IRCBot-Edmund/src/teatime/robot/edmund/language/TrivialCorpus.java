package teatime.robot.edmund.language;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.teatime.common.util.LogUtil;

public class TrivialCorpus {

	private String corpusFile;
	
	private int corpusCount = 0;
	private Map<Integer, String> corpusMap = new HashMap<Integer, String>();
//	private Random random = new Random();
	
	
	public void init(String corpusFileName) throws FileNotFoundException {
		LogUtil.info("initialize corpus from file: " + corpusFileName);
		
		Scanner fin = new Scanner(new FileInputStream(corpusFileName));
		while(fin.hasNextLine()) {
			String line = fin.nextLine();
			corpusMap.put(new Integer(corpusCount++), line);
		}
		
		fin.close();
	}
	
	public int getCorpusCount() {
		return corpusCount;
	}

	public String getRandomLine() {
		
		if(corpusCount == 0)
			return "Hi! Nice weather today, isn't it?";
		else {
			int random = (int)Math.floor(corpusCount * Math.random());
			return corpusMap.get(
					new Integer(random));
		}
	}
	
	public static void main(String[] args) {
		TrivialCorpus corpus = new TrivialCorpus();
		try {
			corpus.init("resources/edmund/trivial_corpus.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String me = "Edmund";
		String you = "terryoy";
		for(int i = 0; i < 20; i++) {
//			System.out.println(
//					MessageFormat.format(
//							corpus.getRandomLine(), new Object[]{me, you}));
			System.out.println(
					corpus.getRandomLine().replace("{0}", me).replace("{1}", you));
		}
	}
}
