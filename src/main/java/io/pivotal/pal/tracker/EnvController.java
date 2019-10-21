package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {
    private final Map<String, String> env = new HashMap<>();

    public EnvController() {
    }
    
    public EnvController(String ports, String memoryLimit, String cfInstanceIndex, String cfInstanceAddress) {
        env.put("PORT", ports);
        env.put("MEMORY_LIMIT", memoryLimit);
        env.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR", cfInstanceAddress);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        return env;
    }
}
