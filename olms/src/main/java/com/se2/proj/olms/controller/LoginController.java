package com.se2.proj.olms.controller;

import com.se2.proj.olms.dto.LoginRequest;
import com.se2.proj.olms.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path = "/login/verification")
    public ResponseEntity<JSONObject> loginVerification(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate user with AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserID(), loginRequest.getPassword())
            );

            // Generate JWT token
            String token = jwtUtil.generateToken(authentication);

            // Create response JSON with token
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);

            return new ResponseEntity<>(jsonObject, HttpStatus.OK);
        } catch (Exception e) {
            JSONObject error = new JSONObject();
            error.put("error", "Invalid credentials");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }
}
