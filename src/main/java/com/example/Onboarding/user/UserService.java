package com.example.Onboarding.user;

import com.example.Onboarding.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;  // The JWT utility for token generation

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Sign-up: Register a new user
    @Transactional
    public String signUp(UserEntity user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // Encode password before saving
        userRepository.save(user);  // Save the new user
        return "User registered successfully!";
    }

    // Sign-in: Authenticate user and return a token
    public String signIn(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Generate JWT Token if credentials are correct
            return jwtUtil.generateToken(user);
        }
        return "Invalid username or password!";
    }
}
