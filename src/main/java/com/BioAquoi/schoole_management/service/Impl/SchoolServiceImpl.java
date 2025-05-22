package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.School;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.SchoolRepo;
import com.BioAquoi.schoole_management.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepo schoolRepo;

    @Override
    public School createSchool(School school) {
        if (schoolRepo.existsByEmail(school.getEmail())) {
            throw new DuplicateEntityException("School with this email already exists");
        }
        return schoolRepo.save(school);
    }

    @Override
    public School updateSchool(School school) {
        School existingSchool = getSchoolById(school.getId());
        // Add update logic here
        return schoolRepo.save(existingSchool);
    }

    @Override
    public School getSchoolById(Long id) {
        return schoolRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("School not found with ID: " + id));
    }

    @Override
    public List<School> getAllSchools() {
        return schoolRepo.findAll();
    }

    @Override
    public void deleteSchool(Long id) {
        School school = getSchoolById(id);
        schoolRepo.delete(school);
    }

    @Override
    public boolean schoolEmailExists(String email) {
        return schoolRepo.existsByEmail(email);
    }
}