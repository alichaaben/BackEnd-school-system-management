package com.BioAquoi.schoole_management.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDto {
    private Long id;
    private Long studentId;
    private LocalDate dueDate;
    private Double totalAmount;
    private String status;
}