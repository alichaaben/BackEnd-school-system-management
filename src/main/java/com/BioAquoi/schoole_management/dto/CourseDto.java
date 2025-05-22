package com.BioAquoi.schoole_management.dto;

import lombok.Data;

@Data
public class CourseDto {
    private Long id;
    private String title;
    private String syllabus;
    private Long classDivisionId;
    private Long subjectId;
}
