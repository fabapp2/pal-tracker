package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.Map;

public class EnvController {
    private final Map<String, String> env = new HashMap<>();

    public EnvController(String ports, String memoryLimit, String cfInstanceIndex, String cfInstanceAddress) {
        env.put("PORT", ports);
        env.put("MEMORY_LIMIT", memoryLimit);
        env.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR", cfInstanceAddress);
    }

    public Map<String, String> getEnv() {
        return env;
    }
}
