package teatime.robot.edmund.language;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ContextManager {

	Map<String, Stack<ChatContext>> contextGroup;
	
	public ContextManager() {
		this.contextGroup = new HashMap<String, Stack<ChatContext>>();
	}
	
	public Stack<ChatContext> getContextStack(String channel, String people) {
		String key = channel + ":" + people;
		Stack<ChatContext> contextStack = contextGroup.get(key);
		if(contextStack == null) {
			contextStack = new Stack<ChatContext>();
			contextGroup.put(key, contextStack);
		}
		
		return contextStack;
	}
	
}
