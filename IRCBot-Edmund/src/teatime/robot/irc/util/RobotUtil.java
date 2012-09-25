package teatime.robot.irc.util;

import java.util.Random;

public class RobotUtil {
	private static Random rand = new Random();
	
	/**
	 * Generate a new name when the name is already in use in IRC channel
	 * 
	 * @param baseName
	 * @return
	 */
	public static String generateName(String baseName) {
		if(baseName.matches(".+_")) {
			return baseName + "1";
		}
		else if(baseName.matches(".+_(\\d+)")) {
			int nameIdx = Integer.parseInt( 
				baseName.substring(baseName.lastIndexOf("_")+1));
			nameIdx++;
			return baseName.substring(0, 
					baseName.lastIndexOf("_") + 1) + nameIdx;
		}
		else {
			return baseName + "_";
		}
	}
	
}
