package com.srijan.mygreetingapp.service;

import com.srijan.mygreetingapp.model.Greeting;
import com.srijan.mygreetingapp.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public String getGreetingMessage(String firstName, String lastName) {
        String message;
        if (firstName != null && lastName != null) {
            message = "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            message = "Hello, " + firstName + "!";
        } else if (lastName != null) {
            message = "Hello, " + lastName + "!";
        } else {
            message = "Hello World!";
        }

        // Save to database
        // Save greeting message in the database
        Greeting greeting = new Greeting(message);
        Greeting savedGreeting = greetingRepository.save(greeting);
        return "Saved with ID: " + savedGreeting.getId();
    }

    //Method to fetch a greeting by id from the repository
    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    public Optional<Greeting> updateGreeting(Long id, String newMessage){
        return greetingRepository.findById(id).map(greeting -> {
            greeting.setMessage(newMessage);
            return greetingRepository.save(greeting);
        });
    }

    public boolean deleteGreeting(Long id) {
        if (greetingRepository.existsById(id)){
            greetingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
