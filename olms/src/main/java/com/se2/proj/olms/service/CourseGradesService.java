package com.se2.proj.olms.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.se2.proj.olms.dto.GetGradesRequest;
import com.se2.proj.olms.dto.SaveGradesRequest;
import com.se2.proj.olms.entities.CourseGrades;
import com.se2.proj.olms.entities.LectureDocument;

@Service
public class CourseGradesService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public JSONObject getGrades(String courseId){
        Query query = new Query();
        query.addCriteria(Criteria.where("courseId").is(courseId));
        JSONObject jObj = new JSONObject(mongoTemplate.find(query, CourseGrades.class));
        return jObj;
    }

    public JSONObject saveGrades(SaveGradesRequest save){
        Query query = new Query();
        //query.addCriteria(Criteria.where("courseId").is(courseId));
        CourseGrades courseGrades = new CourseGrades();
        courseGrades.setAssignmentId(save.getAssignmentId());
        courseGrades.setAssignmentResults(save.getAssignmentResults());
        courseGrades.setCourseId(save.getCourseId());
        courseGrades.setQuizId(save.getQuizId());
        courseGrades.setQuizResults(save.getQuizResults());
        mongoTemplate.save(courseGrades);
        JSONObject jObj = new JSONObject(mongoTemplate.save(courseGrades));
        return jObj;
    }

}
