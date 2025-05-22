package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {
    List<Subject> findByClassDivisionId(Long classDivisionId);
    boolean existsByNameAndClassDivisionId(String name, Long classDivisionId);
}
