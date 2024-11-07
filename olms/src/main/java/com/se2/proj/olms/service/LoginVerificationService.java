package com.se2.proj.olms.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.se2.proj.olms.dao.UserRepository;
import com.se2.proj.olms.dto.UserRepositoryImpl;
import com.se2.proj.olms.entities.User;
import com.se2.proj.olms.security.MyDecryptionUtils;
import com.se2.proj.olms.utils.Validation;

@Service
public class LoginVerificationService {

	// private final MyDecryptionUtils passwordEncoder = new MyDecryptionUtils();

	//@Autowired
	private UserRepository userRepository = new UserRepositoryImpl();

	public String loginAuth(String userID, String password) throws Exception {
		JSONObject user = userRepository.findByUserId(Validation.emptyCheck(userID));
		JSONObject jObj = new JSONObject();
		if (MyDecryptionUtils.decrypt((String) user.get("password")).equals(Validation.emptyCheck(password))) {
			jObj.put("password", password);
			jObj.put("username", user.get("username"));
			jObj.put("authentication", "Valid Authentication");
			jObj.put("userType", user.get("userType"));
			//jObj.remove("empty");
		}
		else {
			jObj.put("password", password);
			jObj.put("username", user.get("username"));
			jObj.put("Authentication", "Invalid username or password");
			//jObj.remove("empty");
		}
		return Validation.emptyCheck(jObj.toString());
	}

}
