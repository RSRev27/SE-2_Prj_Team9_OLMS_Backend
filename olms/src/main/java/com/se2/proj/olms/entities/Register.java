package com.se2.proj.olms.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "LoginTable")
public class Register {

    @Id
    private String id;
    private String userID;
    private String password;
    private String fullName;
    private String userType;
    
}
