package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.Invoice;
import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(Invoice invoice);
    Invoice getInvoiceById(Long id);
    List<Invoice> getInvoicesByStudent(Long studentId);
    List<Invoice> getInvoicesByStatus(String status);
    List<Invoice> getOverdueInvoices(LocalDate currentDate);
    void updateInvoiceStatus(Long id, String status);
    void deleteInvoice(Long id);
}
