package com.BioAquoi.schoole_management.dto;

import lombok.Data;

@Data
public class ExamResultDto {
    private Long id;
    private Long examId;
    private Long studentId;
    private Double score;
    private String grade;
}
