package com.se2.proj.olms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.se2.proj.olms.service.LoginVerificationService;

@RestController
public class LoginController {

	@Autowired
	LoginVerificationService lvs;
	
	@CrossOrigin
	@GetMapping(path = "/login/verification")
	public Response loginVerification(@RequestBody String userID, @RequestBody String password) {
		lvs.loginAuth(userID, password);
		return new Response(200, null);
		
		
	}
	
}
