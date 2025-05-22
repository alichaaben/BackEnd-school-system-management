package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultRepo extends JpaRepository<ExamResult, Long> {
    List<ExamResult> findByExamId(Long examId);
    List<ExamResult> findByStudentId(Long studentId);
    boolean existsByExamIdAndStudentId(Long examId, Long studentId);
}
