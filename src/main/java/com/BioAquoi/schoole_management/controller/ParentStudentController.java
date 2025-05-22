package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.ParentStudentDto;
import com.BioAquoi.schoole_management.entity.ParentStudent;
import com.BioAquoi.schoole_management.service.ParentStudentService;
import com.BioAquoi.schoole_management.mapper.ParentStudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parent-students")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ParentStudentController {

    private final ParentStudentService parentStudentService;
    private final ParentStudentMapper parentStudentMapper;

    @PostMapping
    public ResponseEntity<ParentStudentDto> createParentStudent(@RequestBody ParentStudentDto parentStudentDto) {
        return ResponseEntity.ok(
                parentStudentMapper.toDto(
                        parentStudentService.createParentStudent(
                                parentStudentMapper.toEntity(parentStudentDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentStudentDto> updateParentStudent(
            @PathVariable Long id, 
            @RequestBody ParentStudentDto parentStudentDto) {
        ParentStudent parentStudent = parentStudentService.getParentStudentById(id);
        parentStudentMapper.updateEntityFromDto(parentStudent, parentStudentDto);
        return ResponseEntity.ok(
                parentStudentMapper.toDto(parentStudentService.updateParentStudent(parentStudent))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentStudentDto> getParentStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(
                parentStudentMapper.toDto(parentStudentService.getParentStudentById(id))
        );
    }

    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<ParentStudentDto>> getParentStudentsByParent(@PathVariable Long parentId) {
        return ResponseEntity.ok(
                parentStudentMapper.toDtoList(parentStudentService.getParentStudentsByParent(parentId))
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ParentStudentDto>> getParentStudentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(
                parentStudentMapper.toDtoList(parentStudentService.getParentStudentsByStudent(studentId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParentStudent(@PathVariable Long id) {
        parentStudentService.deleteParentStudent(id);
        return ResponseEntity.noContent().build();
    }
}
