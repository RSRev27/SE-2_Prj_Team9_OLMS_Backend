package com.se2.proj.olms.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se2.proj.olms.dto.LoginRequest;
import com.se2.proj.olms.service.LoginVerificationService;
import com.se2.proj.olms.utils.Validation;

@RestController
@CrossOrigin
public class LoginController {
	
	@Autowired
	LoginVerificationService lvs;

	/*
	 * @Autowired private AuthenticationManager authenticationManager;
	 * 
	 * @Autowired private JwtUtil jwtUtil;
	 */

    @PostMapping(path = "/login/verification")
    public ResponseEntity<String> loginVerification(@RequestBody LoginRequest loginRequest) {
        try {
        	/*    // Authenticate user with AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserID(), loginRequest.getPassword())
            );*/

            // Generate JWT token
            //String token = jwtUtil.generateToken(authentication);

            // Create response JSON with token
			/*
			 * JSONObject jObj = new JSONObject();
			 * 
			 * //jsonObject.put("token", token);
			 * 
			 * jObj.put("username", loginRequest.getUserID()); jObj.put("password",
			 * loginRequest.getPassword());
			 *///jObj.put("username", loginRequest.getUserID());
            
            String response;// = new JSONObject();
            response = lvs.loginAuth(Validation.emptyCheck(loginRequest.getUserID()), Validation.emptyCheck(loginRequest.getPassword()));
            //System.out.println(response);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
        	e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("error", "Invalid credentials");
            return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
        }
    }

}
