package com.se2.proj.olms.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "CourseGrades")
public class CourseGrades {
    @Id
    String Id;
    String courseId;
    String quizId;
    String quizResults;
    String assignmentId;
    String assignmentResults;
    //String visibility;
}
