package com.se2.proj.olms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.se2.proj.olms.dto.SaveGradesRequest;
import com.se2.proj.olms.entities.CourseGrades;

@Service
public class CourseGradesService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Object[] getGrades(String courseId, String userType, String studentName) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(Criteria.where("courseId").is(courseId), Criteria.where("studentName").is(studentName)));
        List<CourseGrades> grades = mongoTemplate.find(query, CourseGrades.class);
        return grades.toArray();
    }

    public CourseGrades saveGrades(SaveGradesRequest save){
        //Query query = new Query();
        //query.addCriteria(Criteria.where("courseId").is(courseId));
        CourseGrades courseGrades = new CourseGrades();
        courseGrades.setAssignmentId(save.getAssignmentId());
        courseGrades.setAssignmentResults(save.getAssignmentResults());
        courseGrades.setCourseId(save.getCourseId());
        courseGrades.setQuizId(save.getQuizId());
        courseGrades.setQuizResults(save.getQuizResults());
        courseGrades.setStudentName(save.getStudentName());
        mongoTemplate.save(courseGrades);
        //JSONObject jObj = new JSONObject(courseGrades);
        return courseGrades;
    }

}
