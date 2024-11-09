package com.se2.proj.olms.dto;
 
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
 
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
 
@Configuration
//@EnableMongoRepositories(basePackages = "com.se2.proj.olms.dto")
@PropertySource("classpath:application.properties")
public class MongoConfig extends AbstractMongoClientConfiguration {
    
	//@Value("${olms.mongodb.uri}")
    private String mongoUri = "mongodb+srv://vxs0570:SE2%40Project@se2project.w4igb.mongodb.net/?retryWrites=true&w=majority&appName=SE2Project";
 
    //@Value("${olms.mongodb.database}")
    private String databaseName = "SE2_Project";
 
    @Override
    protected String getDatabaseName() {
        return databaseName;
    }
 
    @Override
    @Bean
    public MongoClient mongoClient() {

		System.out.println(mongoUri);
		System.out.println(databaseName);
        if (mongoUri == null || mongoUri.isEmpty()) {
            throw new IllegalStateException("MongoDB connection string not configured. Please check application.properties");
        }
 
        try {

			ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

			MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoUri))
                .serverApi(serverApi)
                .build();
 
            return MongoClients.create(settings);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create MongoDB client: " + e.getMessage(), e);
        }
    }
 
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}