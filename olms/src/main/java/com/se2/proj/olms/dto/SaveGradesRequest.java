package com.se2.proj.olms.dto;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class SaveGradesRequest {
    
    @Id
    private String Id;
    private String courseId;
    private String quizId;
    private String quizResults;
    private String assignmentId;
    String assignmentResults;
    //boolean edit;
    private String userType;
    private String studentName;
}
