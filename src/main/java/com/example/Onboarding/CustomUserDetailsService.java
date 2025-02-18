package com.example.Onboarding;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Assuming you have a UserRepository that fetches users from the database
    // private final UserRepository userRepository;

    // Constructor for dependency injection (you can inject a user repository here)
    public CustomUserDetailsService() {
        // Initialize the repository here if needed
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user details from the database (this is just an example)
        // Replace this with actual user data from your repository or service layer

        // For illustration purposes, let's use a dummy user:
        if ("testUser".equals(username)) {
            return new CustomUserDetails(
                    "testUser",
                    "$2a$10$W/2Z6p3XsYayA8V/2vchj5VJlYNlC5vTmn8QXg2FL7/9FjZSEvqG6",  // Example hashed password
                    new ArrayList<>()
            );
        }

        // If no user is found, throw an exception
        throw new UsernameNotFoundException("User not found: " + username);
    }
}
