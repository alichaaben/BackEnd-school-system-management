package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepo extends JpaRepository<Section, Long> {
    List<Section> findByClassDivisionId(Long classDivisionId);
    boolean existsByNameAndClassDivisionId(String name, Long classDivisionId);
}
