package com.srijan.mygreetingapp.controller;

import com.srijan.mygreetingapp.service.GreetingService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private final GreetingService greetingServices;

    public GreetingController() {
        this.greetingServices = new GreetingService();
    }

    @GetMapping
    public Map<String, String> getGreeting() {
        return  Map.of("message", greetingServices.getGreetingMessage());
    }

    @PostMapping
    public Map<String, String> postGreeting(@RequestBody Map<String, String> body) {
        return Map.of("message", "Hello POST Request! ", "data", body.get("name"));
    }

    @PutMapping
    public Map<String, String> putGreeting(@RequestBody Map<String, String> body) {
        return Map.of("message", "Hello PUT Request!", "data", body.get("name"));
    }

    @DeleteMapping
    public Map<String, String> deleteGreeting() {
        return Map.of("message", "Hello, DELETE Request!");
    }
}
