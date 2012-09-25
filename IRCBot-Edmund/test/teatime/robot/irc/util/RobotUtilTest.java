package teatime.robot.irc.util;

public class RobotUtilTest {

	private static void testGenerateName() {
		String case1 = "test";
		String case2 = "test_";
		String case3 = "test_1";
		String case4 = "test_123";
		
		System.out.println(
				RobotUtil.generateName(case1));
		System.out.println(
				RobotUtil.generateName(case2));
		System.out.println(
				RobotUtil.generateName(case3));
		System.out.println(
				RobotUtil.generateName(case4));
	}
	

	public static void main(String[] args) {
		testGenerateName();
	}

}
