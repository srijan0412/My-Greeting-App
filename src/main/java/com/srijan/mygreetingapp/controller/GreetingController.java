package com.srijan.mygreetingapp.controller;

import com.srijan.mygreetingapp.service.GreetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.srijan.mygreetingapp.model.Greeting;

import java.util.Map;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public Map<String, String> getGreeting(@RequestParam(required = false) String firstName,@RequestParam(required = false) String lastName) {
        return  Map.of("message", greetingService.getGreetingMessage(firstName, lastName));
    }

    // Taking id from the user
    @GetMapping("/{id}")
    public Optional<Greeting> getGreetingById(@PathVariable Long id) {
        return greetingService.getGreetingById(id);
    }

    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    @PutMapping("/{id}")
    public Optional<Greeting> updateGreeting(@PathVariable Long id, @RequestBody Map<String, String> request){
        return greetingService.updateGreeting(id, request.get("message"));
    }

    @PostMapping
    public Map<String, String> postGreeting(@RequestBody Map<String, String> body) {
        return Map.of("message", "Hello POST Request! ", "data", body.get("name"));
    }

    @PutMapping
    public Map<String, String> putGreeting(@RequestBody Map<String, String> body) {
        return Map.of("message", "Hello PUT Request!", "data", body.get("name"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGreeting(@PathVariable Long id) {
        if (greetingService.deleteGreeting(id)) {
            return ResponseEntity.ok("Greeting deleted Successfully!");
        }
        return ResponseEntity.status(404).body("Greeting not found!");
    }
}
