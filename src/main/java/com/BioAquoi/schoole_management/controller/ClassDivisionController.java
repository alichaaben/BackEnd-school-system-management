package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.ClassDivisionDto;
import com.BioAquoi.schoole_management.entity.ClassDivision;
import com.BioAquoi.schoole_management.service.ClassDivisionService;
import com.BioAquoi.schoole_management.mapper.ClassDivisionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-divisions")
@RequiredArgsConstructor
public class ClassDivisionController {

        private final ClassDivisionService classDivisionService;
        private final ClassDivisionMapper classDivisionMapper;

        @PostMapping
        public ResponseEntity<ClassDivisionDto> createClassDivision(@RequestBody ClassDivisionDto classDivisionDto) {
                return ResponseEntity.ok(
                                classDivisionMapper.toDto(
                                                classDivisionService.createClassDivision(
                                                                classDivisionMapper.toEntity(classDivisionDto))));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ClassDivisionDto> updateClassDivision(
                        @PathVariable Long id,
                        @RequestBody ClassDivisionDto classDivisionDto) {
                ClassDivision classDivision = classDivisionService.getClassDivisionById(id);
                classDivisionMapper.updateEntityFromDto(classDivision, classDivisionDto);
                return ResponseEntity.ok(
                                classDivisionMapper.toDto(classDivisionService.updateClassDivision(classDivision)));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ClassDivisionDto> getClassDivisionById(@PathVariable Long id) {
                return ResponseEntity.ok(
                                classDivisionMapper.toDto(classDivisionService.getClassDivisionById(id)));
        }

        @GetMapping
        public ResponseEntity<List<ClassDivisionDto>> getAllClassDivisions() {
                return ResponseEntity.ok(
                                classDivisionMapper.toDtoList(classDivisionService.getAllClassDivisions()));
        }

        @GetMapping("/school/{schoolId}")
        public ResponseEntity<List<ClassDivisionDto>> getClassDivisionsBySchool(@PathVariable Long schoolId) {
                return ResponseEntity.ok(
                                classDivisionMapper
                                                .toDtoList(classDivisionService.getClassDivisionsBySchool(schoolId)));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteClassDivision(@PathVariable Long id) {
                classDivisionService.deleteClassDivision(id);
                return ResponseEntity.noContent().build();
        }
}
