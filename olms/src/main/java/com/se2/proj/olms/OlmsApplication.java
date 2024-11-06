package com.se2.proj.olms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.se2.proj.olms.dto")
@ComponentScan(basePackages = "com.se2.proj.olms.*")
@EnableMongoRepositories
public class OlmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlmsApplication.class, args);
	}

}
