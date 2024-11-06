package com.se2.proj.olms.dto;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;

public class UserRepository { // extends MongoRepository<User, String> {
	/* User findByUsername(String username); */

	@Autowired
	MongoConfig test;

	public String selectQuery() { // test = new
		MongoClient mongoClient = test.mongoClient();
		Document query = new Document("username", "admin");
		FindIterable<Document> user = mongoClient.getDatabase("SE2_Project").getCollection("LoginTable").find(query);
		/*
		 * for (Document document : user) {
		 * System.out.println(document.get("password")); }
		 */
		return user.first().toJson();
	}

}
