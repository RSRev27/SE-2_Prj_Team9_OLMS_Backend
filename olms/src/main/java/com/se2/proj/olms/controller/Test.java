package com.se2.proj.olms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
public class Test {
    
    //@Autowired
    //private UserRepository userRepository;

    // BCryptPasswordEncoder instance for password encryption
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Method to create a user with encrypted password
    public String createUser(String username, String password, String userType) {
        String encryptedPassword = passwordEncoder.encode(password); // Encrypt the password
        //User user = new User(username, encryptedPassword, userType); // Create a User object
        return encryptedPassword; // Save the user to MongoDB
    }

    // Method to validate user credentials during login
    public boolean validateUser(String username, String password) {
        //User user = userRepository.findByUsername(username); // Find the user by username
        return true;
        //user != null && passwordEncoder.matches(password, user.getPassword()); // Check if the password matches
    }

    public static void main(String[] args) {
        System.out.println("This my encryptedPassword::"+new Test().createUser("admin", "student", "admin"));
    }
}
