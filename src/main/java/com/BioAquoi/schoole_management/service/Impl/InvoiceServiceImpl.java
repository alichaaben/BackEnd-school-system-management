package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.Invoice;
import com.BioAquoi.schoole_management.entity.Student;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.repository.InvoiceRepo;
import com.BioAquoi.schoole_management.repository.StudentRepo;
import com.BioAquoi.schoole_management.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepo invoiceRepo;
    private final StudentRepo studentRepo;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepo.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice existingInvoice = getInvoiceById(invoice.getId());
        // Add update logic here
        return invoiceRepo.save(existingInvoice);
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found with ID: " + id));
    }

    @Override
    public List<Invoice> getInvoicesByStudent(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        return invoiceRepo.findByStudentId(student.getId());
    }

    @Override
    public List<Invoice> getInvoicesByStatus(String status) {
        return invoiceRepo.findByStatus(status);
    }

    @Override
    public List<Invoice> getOverdueInvoices(LocalDate currentDate) {
        return invoiceRepo.findByDueDateBeforeAndStatusNot(currentDate, "Paid");
    }

    @Override
    public void updateInvoiceStatus(Long id, String status) {
        Invoice invoice = getInvoiceById(id);
        invoice.setStatus(status);
        invoiceRepo.save(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        Invoice invoice = getInvoiceById(id);
        invoiceRepo.delete(invoice);
    }
}
