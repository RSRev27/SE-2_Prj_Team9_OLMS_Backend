package com.se2.proj.olms.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Document(collection = "Lectures")
public class LectureDocument {
    @Id
    private String id;
    private String courseId;
    private String title;
    private String filename;
    private String filePath;
    private LocalDateTime uploadDate;
    private String fileType;
    private long fileSize;
}