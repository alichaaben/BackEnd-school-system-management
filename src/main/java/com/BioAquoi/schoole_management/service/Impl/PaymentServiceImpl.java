package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.Invoice;
import com.BioAquoi.schoole_management.entity.Payment;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.repository.InvoiceRepo;
import com.BioAquoi.schoole_management.repository.PaymentRepo;
import com.BioAquoi.schoole_management.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;
    private final InvoiceRepo invoiceRepo;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        Payment existingPayment = getPaymentById(payment.getId());
        // Add update logic here
        return paymentRepo.save(existingPayment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with ID: " + id));
    }

    @Override
    public List<Payment> getPaymentsByInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepo.findById(invoiceId)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found with ID: " + invoiceId));
        return paymentRepo.findByInvoiceId(invoice.getId());
    }

    @Override
    public List<Payment> getPaymentsByDateRange(LocalDate start, LocalDate end) {
        return paymentRepo.findByPaymentDateBetween(start, end);
    }

    @Override
    public List<Payment> getPaymentsByMethod(String paymentMethod) {
        return paymentRepo.findByPaymentMethod(paymentMethod);
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = getPaymentById(id);
        paymentRepo.delete(payment);
    }
}
