package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {
    List<Staff> findBySchoolId(Long schoolId);
    boolean existsByUserId(Long userId);
    Staff findByUserId(Long userId);
}