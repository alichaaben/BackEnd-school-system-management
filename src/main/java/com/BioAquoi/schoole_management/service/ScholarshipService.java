package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.Scholarship;
import java.util.List;

public interface ScholarshipService {
    Scholarship createScholarship(Scholarship scholarship);
    Scholarship updateScholarship(Scholarship scholarship);
    Scholarship getScholarshipById(Long id);
    List<Scholarship> getScholarshipsByStudent(Long studentId);
    List<Scholarship> getScholarshipsByType(String type);
    void deleteScholarship(Long id);
}