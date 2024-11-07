package com.se2.proj.olms.dto;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class SaveGradesRequest {
    
    @Id
    String Id;
    String courseId;
    String quizId;
    String quizResults;
    String assignmentId;
    String assignmentResults;
    boolean edit;
}
