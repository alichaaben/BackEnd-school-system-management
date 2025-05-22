package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.TeacherAssignmentDto;
import com.BioAquoi.schoole_management.entity.TeacherAssignment;
import com.BioAquoi.schoole_management.service.TeacherAssignmentService;
import com.BioAquoi.schoole_management.mapper.TeacherAssignmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher-assignments")
@RequiredArgsConstructor
public class TeacherAssignmentController {

    private final TeacherAssignmentService assignmentService;
    private final TeacherAssignmentMapper assignmentMapper;

    @PostMapping
    public ResponseEntity<TeacherAssignmentDto> createAssignment(@RequestBody TeacherAssignmentDto assignmentDto) {
        return ResponseEntity.ok(
                assignmentMapper.toDto(
                        assignmentService.createAssignment(
                                assignmentMapper.toEntity(assignmentDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherAssignmentDto> updateAssignment(
            @PathVariable Long id, 
            @RequestBody TeacherAssignmentDto assignmentDto) {
        TeacherAssignment assignment = assignmentService.getAssignmentById(id);
        assignmentMapper.updateEntityFromDto(assignment, assignmentDto);
        return ResponseEntity.ok(
                assignmentMapper.toDto(assignmentService.updateAssignment(assignment))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherAssignmentDto> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(
                assignmentMapper.toDto(assignmentService.getAssignmentById(id))
        );
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TeacherAssignmentDto>> getAssignmentsByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(
                assignmentMapper.toDtoList(assignmentService.getAssignmentsByTeacher(teacherId))
        );
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<TeacherAssignmentDto>> getAssignmentsBySubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(
                assignmentMapper.toDtoList(assignmentService.getAssignmentsBySubject(subjectId))
        );
    }

    @GetMapping("/class/{classDivisionId}")
    public ResponseEntity<List<TeacherAssignmentDto>> getAssignmentsByClassDivision(@PathVariable Long classDivisionId) {
        return ResponseEntity.ok(
                assignmentMapper.toDtoList(assignmentService.getAssignmentsByClassDivision(classDivisionId))
        );
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<TeacherAssignmentDto>> getAssignmentsBySection(@PathVariable Long sectionId) {
        return ResponseEntity.ok(
                assignmentMapper.toDtoList(assignmentService.getAssignmentsBySection(sectionId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
