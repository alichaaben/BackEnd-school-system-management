package com.BioAquoi.schoole_management.dto;

import lombok.Data;

@Data
public class TeacherAssignmentDto {
    private Long id;
    private Long teacherId;
    private Long subjectId;
    private Long classDivisionId;
    private Long sectionId;
}
