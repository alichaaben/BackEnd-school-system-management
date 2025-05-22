package com.BioAquoi.schoole_management.service;


import com.BioAquoi.schoole_management.entity.ClassDivision;
import java.util.List;

public interface ClassDivisionService {
    ClassDivision createClassDivision(ClassDivision classDivision);
    ClassDivision updateClassDivision(ClassDivision classDivision);
    ClassDivision getClassDivisionById(Long id);
    List<ClassDivision> getAllClassDivisions();
    List<ClassDivision> getClassDivisionsBySchool(Long schoolId);
    void deleteClassDivision(Long id);
    boolean classDivisionNameExists(String name, Long schoolId);
}
