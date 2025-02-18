package com.example.Onboarding.user;

import com.example.Onboarding.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint for user sign-up
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserEntity user) {
        String response = userService.signUp(user);
        if (response.equals("User registered successfully!")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Endpoint for user sign-in
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody UserEntity user) {
        // Extract username and password from the UserEntity object
        String username = user.getUsername();
        String password = user.getPassword();

        // Try to sign in the user and get the JWT token
        String token = userService.signIn(username, password);

        // If token is successfully generated
        if (!token.startsWith("Invalid")) {
            return ResponseEntity.ok("User successfully signed in! Token: " + token);
        }

        // If credentials are invalid
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}
