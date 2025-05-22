package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.ExamResultDto;
import com.BioAquoi.schoole_management.entity.ExamResult;
import com.BioAquoi.schoole_management.service.ExamResultService;
import com.BioAquoi.schoole_management.mapper.ExamResultMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam-results")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExamResultController {

    private final ExamResultService examResultService;
    private final ExamResultMapper examResultMapper;

    @PostMapping
    public ResponseEntity<ExamResultDto> createExamResult(@RequestBody ExamResultDto examResultDto) {
        return ResponseEntity.ok(
                examResultMapper.toDto(
                        examResultService.createExamResult(
                                examResultMapper.toEntity(examResultDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResultDto> updateExamResult(
            @PathVariable Long id, 
            @RequestBody ExamResultDto examResultDto) {
        ExamResult examResult = examResultService.getExamResultById(id);
        examResultMapper.updateEntityFromDto(examResult, examResultDto);
        return ResponseEntity.ok(
                examResultMapper.toDto(examResultService.updateExamResult(examResult))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResultDto> getExamResultById(@PathVariable Long id) {
        return ResponseEntity.ok(
                examResultMapper.toDto(examResultService.getExamResultById(id))
        );
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamResultDto>> getExamResultsByExam(@PathVariable Long examId) {
        return ResponseEntity.ok(
                examResultMapper.toDtoList(examResultService.getExamResultsByExam(examId))
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ExamResultDto>> getExamResultsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(
                examResultMapper.toDtoList(examResultService.getExamResultsByStudent(studentId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamResult(@PathVariable Long id) {
        examResultService.deleteExamResult(id);
        return ResponseEntity.noContent().build();
    }
}
