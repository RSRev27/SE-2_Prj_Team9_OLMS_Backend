package com.se2.proj.olms;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.se2.proj.olms.entities.LectureDocument;
import com.se2.proj.olms.service.LectureService;

public class LectureServiceTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @Mock
    private MultipartFile file;

    @InjectMocks
    private LectureService lectureService;

    @Value("${file.upload-dir}")
    private String uploadDir = "uploads/";

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        lectureService.uploadDir = "target/test-uploads";

        
        Path path = Paths.get(lectureService.uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    @Test
    void testGetLecturesByCourseId() {
        
        String courseId = "course123";
        LectureDocument lecture = new LectureDocument();
        lecture.setCourseId(courseId);

        when(mongoTemplate.find(any(Query.class), eq(LectureDocument.class)))
            .thenReturn(Collections.singletonList(lecture));

        
        List<LectureDocument> lectures = lectureService.getLecturesByCourseId(courseId);

        
        assertNotNull(lectures);
        assertEquals(1, lectures.size());
        assertEquals(courseId, lectures.get(0).getCourseId());

        verify(mongoTemplate).find(any(Query.class), eq(LectureDocument.class));
    }

    @Test
    void testUploadLecture() throws Exception {
        
        String courseId = "course123";
        String originalFilename = "lecture.pdf";
        Path courseDir = Paths.get(uploadDir, courseId);
        String filename = System.currentTimeMillis() + "_" + originalFilename;
        Path filePath = courseDir.resolve(filename);

        when(file.getOriginalFilename()).thenReturn(originalFilename);
        when(file.getInputStream()).thenReturn(new ByteArrayInputStream("Test file content".getBytes()));
        when(file.getContentType()).thenReturn("application/pdf");
        when(file.getSize()).thenReturn(1024L);

        
        LectureDocument lecture = lectureService.uploadLecture(courseId, file);

        
        assertNotNull(lecture);
        assertEquals(courseId, lecture.getCourseId());
        assertEquals(originalFilename, lecture.getTitle());
        assertEquals("application/pdf", lecture.getFileType());
        assertEquals(1024L, lecture.getFileSize());

        verify(mongoTemplate).save(any(LectureDocument.class));
    }

    @Test
    void testDeleteLecture() throws Exception {
        
        String lectureId = "lectureId123";
        LectureDocument lecture = new LectureDocument();
        lecture.setId(lectureId);
        lecture.setFilePath(Paths.get(uploadDir, "course123", "lecture.pdf").toString());

        when(mongoTemplate.findOne(any(Query.class), eq(LectureDocument.class))).thenReturn(lecture);

        
        lectureService.deleteLecture(lectureId);

        
        verify(mongoTemplate).remove(any(Query.class), eq(LectureDocument.class));
        verify(mongoTemplate).findOne(any(Query.class), eq(LectureDocument.class));
    }

    @AfterEach
    void tearDown() throws Exception {
        
        Path path = Paths.get(lectureService.uploadDir);
        Files.walk(path)
            .map(Path::toFile)
            .forEach(file -> {
                if (file.exists()) {
                    file.delete();
                }
            });
    }
}
