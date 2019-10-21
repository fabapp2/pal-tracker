package io.pivotal.pal.tracker;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    private final String message;

    public WelcomeController() {
        this.message = "Hello from test";
    }

    public WelcomeController(String message) {
        this.message = message;
    }

    @GetMapping("/")
    public String sayHello() {
        return message;
    }
}
