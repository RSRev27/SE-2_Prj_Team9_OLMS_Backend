package com.se2.proj.olms.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.se2.proj.olms.entities.LectureDocument;

import java.util.List;
 
public interface LectureRepository extends MongoRepository<LectureDocument, String> {
    List<LectureDocument> findByCourseId(String courseId);
}
