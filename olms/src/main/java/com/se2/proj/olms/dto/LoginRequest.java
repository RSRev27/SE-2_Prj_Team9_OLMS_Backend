package com.se2.proj.olms.dto;

import lombok.Data;

@Data
public class LoginRequest {
    
    private String userID;
    private String password;
    private String fullName;
    private String role;
    
}
