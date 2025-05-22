package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.InvoiceDto;
import com.BioAquoi.schoole_management.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    
    @Mapping(source = "student.id", target = "studentId")
    InvoiceDto toDto(Invoice invoice);
    
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "payments", ignore = true)
    Invoice toEntity(InvoiceDto invoiceDto);
    
    List<InvoiceDto> toDtoList(List<Invoice> invoices);
    
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "payments", ignore = true)
    void updateEntityFromDto(@MappingTarget Invoice invoice, InvoiceDto invoiceDto);
}