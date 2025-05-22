package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
    List<Attendance> findByDate(LocalDate date);
    List<Attendance> findByStudentClassDivisionIdAndDate(Long classDivisionId, LocalDate date);
    boolean existsByStudentIdAndDate(Long studentId, LocalDate date);
}