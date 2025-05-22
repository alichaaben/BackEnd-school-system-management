package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.ClassDivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDivisionRepo extends JpaRepository<ClassDivision, Long> {
    List<ClassDivision> findBySchoolId(Long schoolId);
    boolean existsByNameAndSchoolId(String name, Long schoolId);
}