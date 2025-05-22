package com.BioAquoi.schoole_management.repository;


import com.BioAquoi.schoole_management.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepo extends JpaRepository<School, Long> {
    Optional<School> findByEmail(String email);
    boolean existsByEmail(String email);
}
