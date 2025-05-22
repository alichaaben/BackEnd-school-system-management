package com.BioAquoi.schoole_management.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExamDto {
    private Long id;
    private String title;
    private Long subjectId;
    private String examType;
    private LocalDateTime startTime;
    private Integer duration;
}