package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.PaymentDto;
import com.BioAquoi.schoole_management.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    
    @Mapping(source = "invoice.id", target = "invoiceId")
    PaymentDto toDto(Payment payment);
    
    @Mapping(target = "invoice", ignore = true)
    Payment toEntity(PaymentDto paymentDto);
    
    List<PaymentDto> toDtoList(List<Payment> payments);
    
    @Mapping(target = "invoice", ignore = true)
    void updateEntityFromDto(@MappingTarget Payment payment, PaymentDto paymentDto);
}