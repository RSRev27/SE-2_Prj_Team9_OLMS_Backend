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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.se2.proj.olms.entities.LectureDocument;

@Service
public class LectureService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${file.upload-dir}")
    public String uploadDir;

    public List<LectureDocument> getLecturesByCourseId(String courseId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("courseId").is(courseId));
        return mongoTemplate.find(query, LectureDocument.class);
    }

    public LectureDocument uploadLecture(String courseId, MultipartFile file) throws Exception {
        Path courseDir = Paths.get(uploadDir, courseId);
        Files.createDirectories(courseDir);

        @SuppressWarnings("null")
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String filename = System.currentTimeMillis() + "_" + originalFilename;
        Path filePath = courseDir.resolve(filename);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        LectureDocument lecture = new LectureDocument();
        lecture.setCourseId(courseId);
        lecture.setTitle(originalFilename);
        lecture.setFilename(filename);
        lecture.setFilePath(filePath.toString());
        lecture.setUploadDate(LocalDateTime.now());
        lecture.setFileType(file.getContentType());
        lecture.setFileSize(file.getSize());

        mongoTemplate.save(lecture);
        return lecture;
    }

    public void deleteLecture(String id) throws Exception {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        LectureDocument lecture = mongoTemplate.findOne(query, LectureDocument.class);
        
        if (lecture == null) {
            throw new RuntimeException("Lecture not found");
        }

        Path filePath = Paths.get(lecture.getFilePath());
        Files.deleteIfExists(filePath);

        mongoTemplate.remove(query, LectureDocument.class);
    }
    
}
