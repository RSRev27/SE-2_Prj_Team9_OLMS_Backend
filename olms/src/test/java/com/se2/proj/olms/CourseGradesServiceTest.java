package com.se2.proj.olms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
//import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.se2.proj.olms.dto.SaveGradesRequest;
import com.se2.proj.olms.entities.CourseGrades;
import com.se2.proj.olms.service.CourseGradesService;

public class CourseGradesServiceTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private CourseGradesService courseGradesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGrades() {
        
        String courseId = "course123";
        String studentName = "John Doe";
        CourseGrades mockGrade = new CourseGrades();
        mockGrade.setCourseId(courseId);
        mockGrade.setStudentName(studentName);

        when(mongoTemplate.find(any(Query.class), any(Class.class)))
            .thenReturn(Collections.singletonList(mockGrade));

        
        Object[] grades = courseGradesService.getGrades(courseId);

        
        assertNotNull(grades);
        assertEquals(1, grades.length);
        assertEquals(courseId, ((CourseGrades) grades[0]).getCourseId());
        assertEquals(studentName, ((CourseGrades) grades[0]).getStudentName());

        
        verify(mongoTemplate).find(any(Query.class), any(Class.class));
    }

    @Test
    void testSaveGrades() {
        
        SaveGradesRequest saveRequest = new SaveGradesRequest();
        saveRequest.setAssignmentId("assignment123");
        saveRequest.setAssignmentResults("90");
        saveRequest.setCourseId("course123");
        saveRequest.setQuizId("quiz123");
        saveRequest.setQuizResults("80");
        saveRequest.setStudentName("John Doe");

        CourseGrades expectedGrade = new CourseGrades();
        expectedGrade.setAssignmentId(saveRequest.getAssignmentId());
        expectedGrade.setAssignmentResults(saveRequest.getAssignmentResults());
        expectedGrade.setCourseId(saveRequest.getCourseId());
        expectedGrade.setQuizId(saveRequest.getQuizId());
        expectedGrade.setQuizResults(saveRequest.getQuizResults());
        expectedGrade.setStudentName(saveRequest.getStudentName());

        
        CourseGrades savedGrade = courseGradesService.saveGrades(saveRequest);

        
        assertNotNull(savedGrade);
        assertEquals(saveRequest.getAssignmentId(), savedGrade.getAssignmentId());
        assertEquals(saveRequest.getAssignmentResults(), savedGrade.getAssignmentResults());
        assertEquals(saveRequest.getCourseId(), savedGrade.getCourseId());
        assertEquals(saveRequest.getQuizId(), savedGrade.getQuizId());
        assertEquals(saveRequest.getQuizResults(), savedGrade.getQuizResults());
        assertEquals(saveRequest.getStudentName(), savedGrade.getStudentName());

        
        verify(mongoTemplate).save(any(CourseGrades.class));
    }
}
