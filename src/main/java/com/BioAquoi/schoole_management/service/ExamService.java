package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.Exam;
import java.time.LocalDateTime;
import java.util.List;

public interface ExamService {
    Exam createExam(Exam exam);
    Exam updateExam(Exam exam);
    Exam getExamById(Long id);
    List<Exam> getAllExams();
    List<Exam> getExamsBySubject(Long subjectId);
    List<Exam> getExamsByType(String examType);
    List<Exam> getExamsBetweenDates(LocalDateTime start, LocalDateTime end);
    void deleteExam(Long id);
}