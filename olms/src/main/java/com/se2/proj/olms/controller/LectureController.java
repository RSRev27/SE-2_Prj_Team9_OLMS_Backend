package com.se2.proj.olms.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.se2.proj.olms.entities.LectureDocument;
import com.se2.proj.olms.service.LectureService;

//public class LectureController {
@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:3000") // Adjust as needed for your React app
public class LectureController {
 
    @Autowired
    private LectureService lectureService;
 
    @GetMapping("/{courseId}/lectures")
    public ResponseEntity<?> getLectures(@PathVariable String courseId) {
        List<LectureDocument> lectures = lectureService.getLecturesByCourseId(courseId);
        Map<String, Object> response = Map.of(
            "title", "Course Lectures",
            "items", lectures.stream()
                .map(lecture -> Map.of(
                    "id", lecture.getId(),
                    "title", lecture.getTitle(),
                    "link", "/api/courses/" + courseId + "/lectures/files/" + lecture.getFilename()
                ))
                .collect(Collectors.toList())
        );
        return ResponseEntity.ok(response);
    }
 
    @PostMapping("/{courseId}/lectures/upload")
    public ResponseEntity<?> uploadLecture(
            @PathVariable String courseId,
            @RequestParam("file") MultipartFile file) {
        try {
            LectureDocument lecture = lectureService.uploadLecture(courseId, file);
            return ResponseEntity.ok(lecture);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("message", "Upload failed: " + e.getMessage()));
        }
    }
 
    @DeleteMapping("/{courseId}/lectures/{lectureId}")
    public ResponseEntity<?> deleteLecture(
            @PathVariable String courseId,
            @PathVariable String lectureId) {
        try {
            lectureService.deleteLecture(lectureId);
            return ResponseEntity.ok(Map.of("message", "Lecture deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(Map.of("message", "Delete failed: " + e.getMessage()));
        }
    }
}
