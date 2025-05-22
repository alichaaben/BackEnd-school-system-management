package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.*;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.*;
import com.BioAquoi.schoole_management.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {

    private final ExamResultRepo examResultRepo;
    private final ExamRepo examRepo;
    private final StudentRepo studentRepo;

    @Override
    public ExamResult createExamResult(ExamResult examResult) {
        if (examResultRepo.existsByExamIdAndStudentId(
                examResult.getExam().getId(),
                examResult.getStudent().getId())) {
            throw new DuplicateEntityException("Exam result already exists for this student and exam");
        }
        return examResultRepo.save(examResult);
    }

    @Override
    public ExamResult updateExamResult(ExamResult examResult) {
        ExamResult existingResult = getExamResultById(examResult.getId());
        // Add update logic here
        return examResultRepo.save(existingResult);
    }

    @Override
    public ExamResult getExamResultById(Long id) {
        return examResultRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam result not found with ID: " + id));
    }

    @Override
    public List<ExamResult> getExamResultsByExam(Long examId) {
        Exam exam = examRepo.findById(examId)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found with ID: " + examId));
        return examResultRepo.findByExamId(exam.getId());
    }

    @Override
    public List<ExamResult> getExamResultsByStudent(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        return examResultRepo.findByStudentId(student.getId());
    }

    @Override
    public void deleteExamResult(Long id) {
        ExamResult examResult = getExamResultById(id);
        examResultRepo.delete(examResult);
    }

    @Override
    public boolean examResultExists(Long examId, Long studentId) {
        return examResultRepo.existsByExamIdAndStudentId(examId, studentId);
    }
}
