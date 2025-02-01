package context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private final Map<String, String> context = new HashMap<>();

    public void set(String key, String value) {
        context.put(key, value);
    }

    public String get(String key) {
        return context.get(key);
    }

    public void reset(String key) {
        context.put(key, "");
    }
}
