package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.InvoiceDto;
import com.BioAquoi.schoole_management.entity.Invoice;
import com.BioAquoi.schoole_management.service.InvoiceService;
import com.BioAquoi.schoole_management.mapper.InvoiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;

    @PostMapping
    public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceDto invoiceDto) {
        return ResponseEntity.ok(
                invoiceMapper.toDto(
                        invoiceService.createInvoice(
                                invoiceMapper.toEntity(invoiceDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDto> updateInvoice(
            @PathVariable Long id, 
            @RequestBody InvoiceDto invoiceDto) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        invoiceMapper.updateEntityFromDto(invoice, invoiceDto);
        return ResponseEntity.ok(
                invoiceMapper.toDto(invoiceService.updateInvoice(invoice))
        );
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<InvoiceDto> updateInvoiceStatus(
            @PathVariable Long id, 
            @PathVariable String status) {
        invoiceService.updateInvoiceStatus(id, status);
        return ResponseEntity.ok(
                invoiceMapper.toDto(invoiceService.getInvoiceById(id))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(
                invoiceMapper.toDto(invoiceService.getInvoiceById(id))
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<InvoiceDto>> getInvoicesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(
                invoiceMapper.toDtoList(invoiceService.getInvoicesByStudent(studentId))
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<InvoiceDto>> getInvoicesByStatus(@PathVariable String status) {
        return ResponseEntity.ok(
                invoiceMapper.toDtoList(invoiceService.getInvoicesByStatus(status))
        );
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<InvoiceDto>> getOverdueInvoices() {
        return ResponseEntity.ok(
                invoiceMapper.toDtoList(invoiceService.getOverdueInvoices(LocalDate.now()))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
