package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.ClassDivision;
import com.BioAquoi.schoole_management.entity.School;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.ClassDivisionRepo;
import com.BioAquoi.schoole_management.repository.SchoolRepo;
import com.BioAquoi.schoole_management.service.ClassDivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassDivisionServiceImpl implements ClassDivisionService {

    private final ClassDivisionRepo classDivisionRepo;
    private final SchoolRepo schoolRepo;

    @Override
    public ClassDivision createClassDivision(ClassDivision classDivision) {
        if (classDivisionRepo.existsByNameAndSchoolId(
                classDivision.getName(), 
                classDivision.getSchool().getId())) {
            throw new DuplicateEntityException("Class division with this name already exists in this school");
        }
        return classDivisionRepo.save(classDivision);
    }

    @Override
    public ClassDivision updateClassDivision(ClassDivision classDivision) {
        ClassDivision existingClass = getClassDivisionById(classDivision.getId());
        // Add update logic here
        return classDivisionRepo.save(existingClass);
    }

    @Override
    public ClassDivision getClassDivisionById(Long id) {
        return classDivisionRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class division not found with ID: " + id));
    }

    @Override
    public List<ClassDivision> getAllClassDivisions() {
        return classDivisionRepo.findAll();
    }

    @Override
    public List<ClassDivision> getClassDivisionsBySchool(Long schoolId) {
        School school = schoolRepo.findById(schoolId)
                .orElseThrow(() -> new EntityNotFoundException("School not found with ID: " + schoolId));
        return classDivisionRepo.findBySchoolId(school.getId());
    }

    @Override
    public void deleteClassDivision(Long id) {
        ClassDivision classDivision = getClassDivisionById(id);
        classDivisionRepo.delete(classDivision);
    }

    @Override
    public boolean classDivisionNameExists(String name, Long schoolId) {
        return classDivisionRepo.existsByNameAndSchoolId(name, schoolId);
    }
}