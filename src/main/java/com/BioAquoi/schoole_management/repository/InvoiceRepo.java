package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
    List<Invoice> findByStudentId(Long studentId);
    List<Invoice> findByStatus(String status);
    List<Invoice> findByDueDateBeforeAndStatusNot(LocalDate date, String status);
}