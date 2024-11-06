package com.se2.proj.olms.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.se2.proj.olms.dto.UserRepository;
import com.se2.proj.olms.entities.User;
import com.se2.proj.olms.security.MyDecryptionUtils;

@Service
public class LoginVerificationService {

	// private final MyDecryptionUtils passwordEncoder = new MyDecryptionUtils();

	@Autowired
	private UserRepository userRepository;

	public JSONObject loginAuth(String userID, String password) throws Exception {
		User user = userRepository.findByUsername(userID);
		JSONObject jObj = new JSONObject();
		if (MyDecryptionUtils.decrypt(user.getPassword()).equals(password)) {
			jObj.put("password", password);
			jObj.put("username", user.getUsername());
			jObj.put("userType", user.getUserType());
		}
		return jObj;
	}

}
