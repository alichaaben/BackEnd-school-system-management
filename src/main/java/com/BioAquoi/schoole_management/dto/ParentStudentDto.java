package com.BioAquoi.schoole_management.dto;

import lombok.Data;

@Data
public class ParentStudentDto {
    private Long id;
    private Long parentId;
    private Long studentId;
}
