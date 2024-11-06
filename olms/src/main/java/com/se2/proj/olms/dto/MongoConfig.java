package com.se2.proj.olms.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Component
//@EnableMongoRepositories(basePackages = "com.se2.proj.olms.dto") // Enable repositories for the specified package
public class MongoConfig extends AbstractMongoClientConfiguration {

	//@Value("${spring.data.mongodb.uri}") // Inject MongoDB URI from application.properties
	private String connString = "mongodb+srv://vxs0570:SE2%40Project@se2project.w4igb.mongodb.net/?retryWrites=true&w=majority&appName=SE2Project";

	@Override
	protected String getDatabaseName() {
		return "SE2Project"; // Replace with your actual database name
	}

	@Bean
	public MongoTemplate mongoTemplate(MongoClient mongoClient) {
		return new MongoTemplate(mongoClient, getDatabaseName()); // Return MongoTemplate instance
	}

	@Bean
	public MongoClient mongoClient() {
		// Create and configure MongoClient with SSL enabled
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(new ConnectionString(connString))
				// .uuidRepresentation(UuidRepresentation.STANDARD) // Optional, based on your
				// needs
				.applyToSslSettings(builder -> builder.enabled(true)) // Enable SSL
				.build();

		return MongoClients.create(settings); // Return the configured MongoClient
	}
}
