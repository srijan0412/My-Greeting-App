package com.srijan.mygreetingapp.service;

import com.srijan.mygreetingapp.model.Greeting;
import com.srijan.mygreetingapp.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        greetingRepository.save(new Greeting(message));
        return message;
    }

    //Method to fetch a greeting by id from the repository
    public Optional<Greeting> getGreetingById(Long id) {
        return greetingRepository.findById(id);
    }
}
