package com.BioAquoi.schoole_management.dto;

import lombok.Data;

@Data
public class StaffDto {
    private Long id;
    private Long userId;
    private Long schoolId;
    private String position;
}
