package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.Payment;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment updatePayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getPaymentsByInvoice(Long invoiceId);
    List<Payment> getPaymentsByDateRange(LocalDate start, LocalDate end);
    List<Payment> getPaymentsByMethod(String paymentMethod);
    void deletePayment(Long id);
}
