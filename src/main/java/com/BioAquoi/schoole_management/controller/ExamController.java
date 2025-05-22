package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.ExamDto;
import com.BioAquoi.schoole_management.entity.Exam;
import com.BioAquoi.schoole_management.service.ExamService;
import com.BioAquoi.schoole_management.mapper.ExamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExamController {

    private final ExamService examService;
    private final ExamMapper examMapper;

    @PostMapping
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto examDto) {
        return ResponseEntity.ok(
                examMapper.toDto(
                        examService.createExam(
                                examMapper.toEntity(examDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDto> updateExam(
            @PathVariable Long id, 
            @RequestBody ExamDto examDto) {
        Exam exam = examService.getExamById(id);
        examMapper.updateEntityFromDto(exam, examDto);
        return ResponseEntity.ok(
                examMapper.toDto(examService.updateExam(exam))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDto> getExamById(@PathVariable Long id) {
        return ResponseEntity.ok(
                examMapper.toDto(examService.getExamById(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<ExamDto>> getAllExams() {
        return ResponseEntity.ok(
                examMapper.toDtoList(examService.getAllExams())
        );
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<ExamDto>> getExamsBySubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(
                examMapper.toDtoList(examService.getExamsBySubject(subjectId))
        );
    }

    @GetMapping("/type/{examType}")
    public ResponseEntity<List<ExamDto>> getExamsByType(@PathVariable String examType) {
        return ResponseEntity.ok(
                examMapper.toDtoList(examService.getExamsByType(examType))
        );
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ExamDto>> getExamsBetweenDates(
            @RequestParam LocalDateTime start, 
            @RequestParam LocalDateTime end) {
        return ResponseEntity.ok(
                examMapper.toDtoList(examService.getExamsBetweenDates(start, end))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}