package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.SchoolDto;
import com.BioAquoi.schoole_management.entity.School;
import com.BioAquoi.schoole_management.service.SchoolService;
import com.BioAquoi.schoole_management.mapper.SchoolMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;
    private final SchoolMapper schoolMapper;

    @PostMapping
    public ResponseEntity<SchoolDto> createSchool(@RequestBody SchoolDto schoolDto) {
        return ResponseEntity.ok(
                schoolMapper.toDto(schoolService.createSchool(schoolMapper.toEntity(schoolDto)))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolDto> updateSchool(@PathVariable Long id, @RequestBody SchoolDto schoolDto) {
        School school = schoolService.getSchoolById(id);
        schoolMapper.updateEntityFromDto(school, schoolDto);
        return ResponseEntity.ok(schoolMapper.toDto(schoolService.updateSchool(school)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDto> getSchoolById(@PathVariable Long id) {
        return ResponseEntity.ok(schoolMapper.toDto(schoolService.getSchoolById(id)));
    }

    @GetMapping
    public ResponseEntity<List<SchoolDto>> getAllSchools() {
        return ResponseEntity.ok(schoolMapper.toDtoList(schoolService.getAllSchools()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }
}
