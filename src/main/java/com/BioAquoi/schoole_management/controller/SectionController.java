package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.SectionDto;
import com.BioAquoi.schoole_management.entity.Section;
import com.BioAquoi.schoole_management.service.SectionService;
import com.BioAquoi.schoole_management.mapper.SectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SectionController {

    private final SectionService sectionService;
    private final SectionMapper sectionMapper;

    @PostMapping
    public ResponseEntity<SectionDto> createSection(@RequestBody SectionDto sectionDto) {
        return ResponseEntity.ok(
                sectionMapper.toDto(
                        sectionService.createSection(
                                sectionMapper.toEntity(sectionDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionDto> updateSection(
            @PathVariable Long id, 
            @RequestBody SectionDto sectionDto) {
        Section section = sectionService.getSectionById(id);
        sectionMapper.updateEntityFromDto(section, sectionDto);
        return ResponseEntity.ok(
                sectionMapper.toDto(sectionService.updateSection(section))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionDto> getSectionById(@PathVariable Long id) {
        return ResponseEntity.ok(
                sectionMapper.toDto(sectionService.getSectionById(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<SectionDto>> getAllSections() {
        return ResponseEntity.ok(
                sectionMapper.toDtoList(sectionService.getAllSections())
        );
    }

    @GetMapping("/class-division/{classDivisionId}")
    public ResponseEntity<List<SectionDto>> getSectionsByClassDivision(@PathVariable Long classDivisionId) {
        return ResponseEntity.ok(
                sectionMapper.toDtoList(sectionService.getSectionsByClassDivision(classDivisionId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
        return ResponseEntity.noContent().build();
    }
}