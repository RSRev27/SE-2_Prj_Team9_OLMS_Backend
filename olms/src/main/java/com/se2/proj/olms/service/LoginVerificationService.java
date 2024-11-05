package com.se2.proj.olms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.se2.proj.olms.dto.UserRepository;

import com.se2.proj.olms.entities.User;

import org.json.JSONObject;

@Service
public class LoginVerificationService {

	 private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	 @Autowired
    private UserRepository userRepository;
	
	public JSONObject loginAuth(String userID, String password) {
		User user = userRepository.findByUsername(userID);
		JSONObject jObj = new JSONObject();
		if(passwordEncoder.matches(password, user.getPassword())) {
			jObj.put("password", password);
			jObj.put("username", user.getUsername());
			jObj.put("userType", user.getUserType());
		}
		return jObj;
	}

}
