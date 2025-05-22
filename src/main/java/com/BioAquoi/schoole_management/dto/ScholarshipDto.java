package com.BioAquoi.schoole_management.dto;


import lombok.Data;

@Data
public class ScholarshipDto {
    private Long id;
    private Long studentId;
    private String type;
    private Double amount;
    private String reason;
}