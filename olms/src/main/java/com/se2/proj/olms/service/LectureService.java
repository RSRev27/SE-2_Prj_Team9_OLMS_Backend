package com.se2.proj.olms.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.se2.proj.olms.dao.LectureRepository;
import com.se2.proj.olms.entities.LectureDocument;

@Service
public class LectureService {
 
    @Autowired
    private LectureRepository lectureRepository;
 
    @Value("${file.upload-dir}")
    private String uploadDir;
 
    public List<LectureDocument> getLecturesByCourseId(String courseId) {
        return lectureRepository.findByCourseId(courseId);
    }
 
    public LectureDocument uploadLecture(String courseId, MultipartFile file) throws Exception {
        // Create course directory if it doesn't exist
        Path courseDir = Paths.get(uploadDir, courseId);
        Files.createDirectories(courseDir);
 
        // Generate unique filename
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String filename = System.currentTimeMillis() + "_" + originalFilename;
        Path filePath = courseDir.resolve(filename);
 
        // Save file to filesystem
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
 
        // Create and save document in MongoDB
        LectureDocument lecture = new LectureDocument();
        lecture.setCourseId(courseId);
        lecture.setTitle(originalFilename);
        lecture.setFilename(filename);
        lecture.setFilePath(filePath.toString());
        lecture.setUploadDate(LocalDateTime.now());
        lecture.setFileType(file.getContentType());
        lecture.setFileSize(file.getSize());
 
        return lectureRepository.save(lecture);
    }
 
    public void deleteLecture(String id) throws Exception {
        LectureDocument lecture = lectureRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lecture not found"));
 
        // Delete file from filesystem
        Path filePath = Paths.get(lecture.getFilePath());
        Files.deleteIfExists(filePath);
 
        // Delete document from MongoDB
        lectureRepository.deleteById(id);
    }
}