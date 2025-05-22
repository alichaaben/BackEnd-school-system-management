package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.ClassDivision;
import com.BioAquoi.schoole_management.entity.Section;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.ClassDivisionRepo;
import com.BioAquoi.schoole_management.repository.SectionRepo;
import com.BioAquoi.schoole_management.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepo sectionRepo;
    private final ClassDivisionRepo classDivisionRepo;

    @Override
    public Section createSection(Section section) {
        if (sectionRepo.existsByNameAndClassDivisionId(
                section.getName(), 
                section.getClassDivision().getId())) {
            throw new DuplicateEntityException("Section with this name already exists in this class division");
        }
        return sectionRepo.save(section);
    }

    @Override
    public Section updateSection(Section section) {
        Section existingSection = getSectionById(section.getId());
        // Add update logic here
        return sectionRepo.save(existingSection);
    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Section not found with ID: " + id));
    }

    @Override
    public List<Section> getAllSections() {
        return sectionRepo.findAll();
    }

    @Override
    public List<Section> getSectionsByClassDivision(Long classDivisionId) {
        ClassDivision classDivision = classDivisionRepo.findById(classDivisionId)
                .orElseThrow(() -> new EntityNotFoundException("Class division not found with ID: " + classDivisionId));
        return sectionRepo.findByClassDivisionId(classDivision.getId());
    }

    @Override
    public void deleteSection(Long id) {
        Section section = getSectionById(id);
        sectionRepo.delete(section);
    }

    @Override
    public boolean sectionNameExists(String name, Long classDivisionId) {
        return sectionRepo.existsByNameAndClassDivisionId(name, classDivisionId);
    }
}