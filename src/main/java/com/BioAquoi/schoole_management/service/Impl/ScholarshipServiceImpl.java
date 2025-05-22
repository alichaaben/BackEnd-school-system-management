package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.Scholarship;
import com.BioAquoi.schoole_management.entity.Student;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.repository.ScholarshipRepo;
import com.BioAquoi.schoole_management.repository.StudentRepo;
import com.BioAquoi.schoole_management.service.ScholarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScholarshipServiceImpl implements ScholarshipService {

    private final ScholarshipRepo scholarshipRepo;
    private final StudentRepo studentRepo;

    @Override
    public Scholarship createScholarship(Scholarship scholarship) {
        return scholarshipRepo.save(scholarship);
    }

    @Override
    public Scholarship updateScholarship(Scholarship scholarship) {
        Scholarship existingScholarship = getScholarshipById(scholarship.getId());
        // Add update logic here
        return scholarshipRepo.save(existingScholarship);
    }

    @Override
    public Scholarship getScholarshipById(Long id) {
        return scholarshipRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Scholarship not found with ID: " + id));
    }

    @Override
    public List<Scholarship> getScholarshipsByStudent(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        return scholarshipRepo.findByStudentId(student.getId());
    }

    @Override
    public List<Scholarship> getScholarshipsByType(String type) {
        return scholarshipRepo.findByType(type);
    }

    @Override
    public void deleteScholarship(Long id) {
        Scholarship scholarship = getScholarshipById(id);
        scholarshipRepo.delete(scholarship);
    }
}
