package com.se2.proj.olms.dto;

import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.se2.proj.olms.dao.UserRepository;

public class UserRepositoryImpl implements UserRepository { // extends MongoRepository<User, String> {
	/* User findByUsername(String username); */

	// @Autowired
	MongoConfig test = new MongoConfig();

	@Override
	public JSONObject findByUserId(String userId) {
		MongoClient mongoClient = test.mongoClient();
		Document query = new Document("username", userId);
		FindIterable<Document> user = mongoClient.getDatabase("SE2_Project").getCollection("LoginTable").find(query);
		/*
		 * for (Document document : user) {
		 * System.out.println(document.get("password")); }
		 */
		JSONObject jObj = new JSONObject();
		jObj.put("username", user.first().get("username"));
		jObj.put("password", user.first().get("password"));
		jObj.put("userType", user.first().get("userType"));
		return jObj;
	}

}
