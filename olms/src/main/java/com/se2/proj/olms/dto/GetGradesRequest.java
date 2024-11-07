package com.se2.proj.olms.dto;

import lombok.Data;

@Data
public class GetGradesRequest {
    String courseId;
    String userType;
}
