package teatime.robot.edmund.language;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ChatContext {

	private Map<String, String> context;

	public boolean containsKey(Object key) {
		return context.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return context.containsValue(value);
	}

	public Set<Entry<String, String>> entrySet() {
		return context.entrySet();
	}

	public String get(Object key) {
		return context.get(key);
	}

	public boolean isEmpty() {
		return context.isEmpty();
	}

	public Set<String> keySet() {
		return context.keySet();
	}

	public String put(String key, String value) {
		return context.put(key, value);
	}

	public void putAll(Map<? extends String, ? extends String> m) {
		context.putAll(m);
	}

	public String remove(Object key) {
		return context.remove(key);
	}

	public int size() {
		return context.size();
	}

	public Collection<String> values() {
		return context.values();
	}
	
}
