package teatime.robot.edmund.szdiy;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SZDIYRoll {
	
	private Integer max = 6;
	private Random rand = new Random();

	public SZDIYRoll() {
		// TODO Auto-generated constructor stub
	}

	public String rollLine(String line) {
		Pattern rollPattern = Pattern.compile("roll[\\s\\(]?(\\d*)[\\)\\s]?");
		Matcher m = rollPattern.matcher(line);
		if (m.find()) {
			
			if (m.group(1).matches("\\d+")) {
				// change max value
				Integer inputMax = Integer.parseInt(m.group(1));
				this.max = inputMax;
			}
			
			return "number: " + this.rand.nextInt(this.max) + " (max " + this.max + ")";
		}
		else {
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		SZDIYRoll roll = new SZDIYRoll();
		while(in.hasNextLine()) {
			
			String line = in.nextLine();
			
			System.out.println("result: " + roll.rollLine(line));
		}
		
	}

}
