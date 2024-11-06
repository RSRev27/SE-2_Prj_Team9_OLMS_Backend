package com.se2.proj.olms.dao;

import org.json.JSONObject;

public interface UserRepository {

	JSONObject findByUserId(String userId);

}
