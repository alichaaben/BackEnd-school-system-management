package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findBySchoolId(Long schoolId);
    List<Student> findByClassDivisionId(Long classDivisionId);
    List<Student> findBySectionId(Long sectionId);
    boolean existsByUserId(Long userId);
}