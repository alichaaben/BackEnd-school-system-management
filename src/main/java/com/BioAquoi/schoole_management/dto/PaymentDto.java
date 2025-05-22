package com.BioAquoi.schoole_management.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDto {
    private Long id;
    private Long invoiceId;
    private LocalDate paymentDate;
    private Double amount;
    private String paymentMethod;
    private String reference;
}
