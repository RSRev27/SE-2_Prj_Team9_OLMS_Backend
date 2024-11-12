package com.se2.proj.olms.controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.se2.proj.olms.dto.GetGradesRequest;
import com.se2.proj.olms.dto.LoginRequest;
import com.se2.proj.olms.entities.Register;
import com.se2.proj.olms.service.CourseService;

@RestController
//@RequestMapping("/courses")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/username")
    public ResponseEntity<String> getUserName(
            @RequestBody LoginRequest requestBody) {
        try {
            //LectureDocument lecture = lectureService.uploadLecture(courseId, file);
            Register results = courseService.getUserName(requestBody.getUserID());//, requestBody.getUserType(), requestBody.getStudentName());
            JSONObject response =  new JSONObject();
            response.put("Name", results.getFullName());
            response.put("Role", results.getUserType());
            return ResponseEntity.ok(response.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new JSONObject("Error in fetching the data").toString());
        }
    }

    @GetMapping("/gettalist")
    public ResponseEntity<?> getTAList() {
        try {
            //LectureDocument lecture = lectureService.uploadLecture(courseId, file);
            Object[] results = courseService.getTAList();//, requestBody.getUserType(), requestBody.getStudentName());
            //JSONObject response =  new JSONObject();
            //response.put("Name", results);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("message", "Error in fetching the list" + e.getMessage()));
        }
    }
    
}
