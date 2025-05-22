package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.Exam;
import com.BioAquoi.schoole_management.entity.Subject;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.repository.ExamRepo;
import com.BioAquoi.schoole_management.repository.SubjectRepo;
import com.BioAquoi.schoole_management.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepo examRepo;
    private final SubjectRepo subjectRepo;

    @Override
    public Exam createExam(Exam exam) {
        return examRepo.save(exam);
    }

    @Override
    public Exam updateExam(Exam exam) {
        Exam existingExam = getExamById(exam.getId());
        // Add update logic here
        return examRepo.save(existingExam);
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found with ID: " + id));
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepo.findAll();
    }

    @Override
    public List<Exam> getExamsBySubject(Long subjectId) {
        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with ID: " + subjectId));
        return examRepo.findBySubjectId(subject.getId());
    }

    @Override
    public List<Exam> getExamsByType(String examType) {
        return examRepo.findByExamType(examType);
    }

    @Override
    public List<Exam> getExamsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return examRepo.findByStartTimeBetween(start, end);
    }

    @Override
    public void deleteExam(Long id) {
        Exam exam = getExamById(id);
        examRepo.delete(exam);
    }
}