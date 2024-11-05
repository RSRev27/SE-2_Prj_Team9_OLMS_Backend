package com.se2.proj.olms.dto;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Connections {
    public static MongoDatabase createConnection() {
        String connectionString = "mongodb+srv://vxs0570:<db_password>@se2project.w4igb.mongodb.net/?retryWrites=true&w=majority&appName=SE2Project";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        
        MongoDatabase database = null;

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            database = mongoClient.getDatabase("admin");
            //database.runCommand(new Document("ping", 1));
            //System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
             
            
        }
        catch (MongoException e) {
            e.printStackTrace();
        }

        return database;

    }
}
