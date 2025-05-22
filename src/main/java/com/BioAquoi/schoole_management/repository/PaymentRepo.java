package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
    List<Payment> findByInvoiceId(Long invoiceId);
    List<Payment> findByPaymentDateBetween(LocalDate start, LocalDate end);
    List<Payment> findByPaymentMethod(String paymentMethod);
}
