package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.PaymentDto;
import com.BioAquoi.schoole_management.entity.Payment;
import com.BioAquoi.schoole_management.service.PaymentService;
import com.BioAquoi.schoole_management.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok(
                paymentMapper.toDto(
                        paymentService.createPayment(
                                paymentMapper.toEntity(paymentDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> updatePayment(
            @PathVariable Long id, 
            @RequestBody PaymentDto paymentDto) {
        Payment payment = paymentService.getPaymentById(id);
        paymentMapper.updateEntityFromDto(payment, paymentDto);
        return ResponseEntity.ok(
                paymentMapper.toDto(paymentService.updatePayment(payment))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(
                paymentMapper.toDto(paymentService.getPaymentById(id))
        );
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<PaymentDto>> getPaymentsByInvoice(@PathVariable Long invoiceId) {
        return ResponseEntity.ok(
                paymentMapper.toDtoList(paymentService.getPaymentsByInvoice(invoiceId))
        );
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PaymentDto>> getPaymentsByDateRange(
            @RequestParam LocalDate start, 
            @RequestParam LocalDate end) {
        return ResponseEntity.ok(
                paymentMapper.toDtoList(paymentService.getPaymentsByDateRange(start, end))
        );
    }

    @GetMapping("/method/{paymentMethod}")
    public ResponseEntity<List<PaymentDto>> getPaymentsByMethod(@PathVariable String paymentMethod) {
        return ResponseEntity.ok(
                paymentMapper.toDtoList(paymentService.getPaymentsByMethod(paymentMethod))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
