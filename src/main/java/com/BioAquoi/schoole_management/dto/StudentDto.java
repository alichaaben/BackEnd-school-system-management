package com.BioAquoi.schoole_management.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDto {
    private Long id;
    private Long userId;
    private Long schoolId;
    private Long classDivisionId;
    private Long sectionId;
    private LocalDate admissionDate;
}