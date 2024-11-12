package com.se2.proj.olms.dto;

import java.util.List;

//import javax.management.Query;

//import org.bson.Document;
import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoClient;
import com.se2.proj.olms.dao.UserRepository;
import com.se2.proj.olms.entities.Register;

public class UserRepositoryImpl implements UserRepository { // extends MongoRepository<User, String> {
	/* User findByUsername(String username); */

	//@Autowired
	//@Autowired
	private MongoTemplate mongoTemplate = new MongoConfig().mongoTemplate();

	@Override
	public JSONObject findByUserId(String userId) {
		//MongoClient mongoClient = test.mongoClient();
		Query query = new Query();
		query.addCriteria(Criteria.where("userID").is(userId));
		//FindIterable<Document> user = mongoClient.getDatabase("SE2_Project").getCollection("LoginTable").find(query);
		List<Register> user = mongoTemplate.find(query, Register.class);
		/*
		 * for (Document document : user) {
		 * System.out.println(document.get("password")); }
		 */
		JSONObject jObj = new JSONObject();
		jObj.put("username", user.get(0).getUserID());
		jObj.put("password", user.get(0).getPassword());//("password"));
		jObj.put("userType", user.get(0).getUserType());//first().get("userType"));
		jObj.put("userId", user.get(0).getId());//first().get("_id"));
		return jObj;
	}

}
