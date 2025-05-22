package com.BioAquoi.schoole_management.service;


import java.util.List;

import com.BioAquoi.schoole_management.entity.School;

public interface SchoolService {
    School createSchool(School school);
    School updateSchool(School school);
    School getSchoolById(Long id);
    List<School> getAllSchools();
    void deleteSchool(Long id);
    boolean schoolEmailExists(String email);
}