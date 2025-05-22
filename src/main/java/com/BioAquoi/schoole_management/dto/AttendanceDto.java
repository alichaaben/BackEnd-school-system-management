package com.BioAquoi.schoole_management.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceDto {
    private Long id;
    private Long studentId;
    private LocalDate date;
    private String status;
    private Long recordedById;
}