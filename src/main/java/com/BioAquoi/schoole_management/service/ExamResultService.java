package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.ExamResult;
import java.util.List;

public interface ExamResultService {
    ExamResult createExamResult(ExamResult examResult);
    ExamResult updateExamResult(ExamResult examResult);
    ExamResult getExamResultById(Long id);
    List<ExamResult> getExamResultsByExam(Long examId);
    List<ExamResult> getExamResultsByStudent(Long studentId);
    void deleteExamResult(Long id);
    boolean examResultExists(Long examId, Long studentId);
}
