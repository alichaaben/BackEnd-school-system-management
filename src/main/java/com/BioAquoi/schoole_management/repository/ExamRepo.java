package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long> {
    List<Exam> findBySubjectId(Long subjectId);
    List<Exam> findByExamType(String examType);
    List<Exam> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}