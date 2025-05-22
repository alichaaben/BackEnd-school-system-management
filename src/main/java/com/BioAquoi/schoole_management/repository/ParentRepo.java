package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {
    Optional<Parent> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}