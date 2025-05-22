package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByUserId(Long userId);
    List<LeaveRequest> findByStatus(String status);
    List<LeaveRequest> findByStartDateBetween(LocalDate start, LocalDate end);
}