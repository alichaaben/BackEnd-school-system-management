package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.ScholarshipDto;
import com.BioAquoi.schoole_management.entity.Scholarship;
import com.BioAquoi.schoole_management.service.ScholarshipService;
import com.BioAquoi.schoole_management.mapper.ScholarshipMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scholarships")
@RequiredArgsConstructor
public class ScholarshipController {

    private final ScholarshipService scholarshipService;
    private final ScholarshipMapper scholarshipMapper;

    @PostMapping
    public ResponseEntity<ScholarshipDto> createScholarship(@RequestBody ScholarshipDto scholarshipDto) {
        return ResponseEntity.ok(
                scholarshipMapper.toDto(
                        scholarshipService.createScholarship(
                                scholarshipMapper.toEntity(scholarshipDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScholarshipDto> updateScholarship(
            @PathVariable Long id, 
            @RequestBody ScholarshipDto scholarshipDto) {
        Scholarship scholarship = scholarshipService.getScholarshipById(id);
        scholarshipMapper.updateEntityFromDto(scholarship, scholarshipDto);
        return ResponseEntity.ok(
                scholarshipMapper.toDto(scholarshipService.updateScholarship(scholarship))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScholarshipDto> getScholarshipById(@PathVariable Long id) {
        return ResponseEntity.ok(
                scholarshipMapper.toDto(scholarshipService.getScholarshipById(id))
        );
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ScholarshipDto>> getScholarshipsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(
                scholarshipMapper.toDtoList(scholarshipService.getScholarshipsByStudent(studentId))
        );
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ScholarshipDto>> getScholarshipsByType(@PathVariable String type) {
        return ResponseEntity.ok(
                scholarshipMapper.toDtoList(scholarshipService.getScholarshipsByType(type))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScholarship(@PathVariable Long id) {
        scholarshipService.deleteScholarship(id);
        return ResponseEntity.noContent().build();
    }
}
