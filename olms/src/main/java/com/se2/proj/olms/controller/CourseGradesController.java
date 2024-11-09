package com.se2.proj.olms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.se2.proj.olms.dto.GetGradesRequest;
import com.se2.proj.olms.dto.SaveGradesRequest;
import com.se2.proj.olms.service.CourseGradesService;

@RestController
@RequestMapping("/grades")
@CrossOrigin
public class CourseGradesController {

    @Autowired
    private CourseGradesService courseGradesService;

    @PostMapping("/getgrades")
    public ResponseEntity<?> getGrades(
            @RequestBody GetGradesRequest requestBody) {
        try {
            //LectureDocument lecture = lectureService.uploadLecture(courseId, file);
            Object[] results = courseGradesService.getGrades(requestBody.getCourseId());//, requestBody.getUserType(), requestBody.getStudentName());
            //JSONObject response =  new JSONObject(results);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("message", "Upload failed: " + e.getMessage()));
        }
    }

    @PostMapping("/savegrades")
    public ResponseEntity<?> saveGrades(@RequestBody SaveGradesRequest requestBody) {
        try {
            //LectureDocument lecture = lectureService.uploadLecture(courseId, file);
            if(!requestBody.getUserType().equals("student")){
                return ResponseEntity.ok(courseGradesService.saveGrades(requestBody));
            }
            else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Not Autherizd"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("message", "Upload failed: " + e.getMessage()));
        }
    }
    
}
