package com.se2.proj.olms.dto;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoIterable;
import com.se2.proj.olms.entities.User;
import com.se2.proj.olms.security.MyEncryptionUtils;
import org.bson.conversions.Bson;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Autowired;

public class MongoDBConnection {
	
	@Autowired
    private static UserRepository userRepository;

	public static void main(String[] args) throws Exception {
		
		  String uri =
		  "mongodb+srv://vxs0570:SE2%40Project@se2project.w4igb.mongodb.net/?retryWrites=true&w=majority&appName=SE2Project";
		  
		  // Configure MongoClientSettings to enable SSL 
		  MongoClientSettings settings =
		  MongoClientSettings.builder() .applyConnectionString(new
		  ConnectionString(uri)) //.uuidRepresentation(UuidRepresentation.STANDARD) //Optional, based on your needs 
		  .applyToSslSettings(builder ->
		  builder.enabled(true)) // Enable SSL 
.build();
		  
		  MongoClient mongoClient = MongoClients.create(settings);
		  
		  // Your database operations here 
		  MongoIterable<String> test =
		  mongoClient.listDatabaseNames(); for (String test1 : test) {
		  System.out.println(test1); }
		  //BsonString filter = new BsonString("admin");
		  Document query = new Document("username", "admin");
		  FindIterable<Document> user = mongoClient.getDatabase("SE2_Project").getCollection("LoginTable").find(query);
		  for (Document document : user) {
			  System.out.println(document.get("password"));
		}
		  
	
		String pwd = "professor";
		System.out.println(MyEncryptionUtils.encrypt(pwd));
	}
}
