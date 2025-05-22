package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Scholarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScholarshipRepo extends JpaRepository<Scholarship, Long> {
    List<Scholarship> findByStudentId(Long studentId);
    List<Scholarship> findByType(String type);
}
