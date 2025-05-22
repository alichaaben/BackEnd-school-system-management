package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.Section;
import java.util.List;

public interface SectionService {
    Section createSection(Section section);
    Section updateSection(Section section);
    Section getSectionById(Long id);
    List<Section> getAllSections();
    List<Section> getSectionsByClassDivision(Long classDivisionId);
    void deleteSection(Long id);
    boolean sectionNameExists(String name, Long classDivisionId);
}
