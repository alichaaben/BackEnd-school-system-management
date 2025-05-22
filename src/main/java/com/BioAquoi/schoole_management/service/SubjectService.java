package com.BioAquoi.schoole_management.service;


import com.BioAquoi.schoole_management.entity.Subject;
import java.util.List;

public interface SubjectService {
    Subject createSubject(Subject subject);
    Subject updateSubject(Subject subject);
    Subject getSubjectById(Long id);
    List<Subject> getAllSubjects();
    List<Subject> getSubjectsByClassDivision(Long classDivisionId);
    void deleteSubject(Long id);
    boolean subjectNameExists(String name, Long classDivisionId);
}
