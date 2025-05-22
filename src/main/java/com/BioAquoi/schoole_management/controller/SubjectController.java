package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.SubjectDto;
import com.BioAquoi.schoole_management.entity.Subject;
import com.BioAquoi.schoole_management.service.SubjectService;
import com.BioAquoi.schoole_management.mapper.SubjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    @PostMapping
    public ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok(
                subjectMapper.toDto(
                        subjectService.createSubject(
                                subjectMapper.toEntity(subjectDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDto> updateSubject(
            @PathVariable Long id, 
            @RequestBody SubjectDto subjectDto) {
        Subject subject = subjectService.getSubjectById(id);
        subjectMapper.updateEntityFromDto(subject, subjectDto);
        return ResponseEntity.ok(
                subjectMapper.toDto(subjectService.updateSubject(subject))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long id) {
        return ResponseEntity.ok(
                subjectMapper.toDto(subjectService.getSubjectById(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        return ResponseEntity.ok(
                subjectMapper.toDtoList(subjectService.getAllSubjects())
        );
    }

    @GetMapping("/class-division/{classDivisionId}")
    public ResponseEntity<List<SubjectDto>> getSubjectsByClassDivision(@PathVariable Long classDivisionId) {
        return ResponseEntity.ok(
                subjectMapper.toDtoList(subjectService.getSubjectsByClassDivision(classDivisionId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}