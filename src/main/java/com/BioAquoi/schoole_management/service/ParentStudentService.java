package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.ParentStudent;
import java.util.List;

public interface ParentStudentService {
    ParentStudent createParentStudent(ParentStudent parentStudent);
    ParentStudent updateParentStudent(ParentStudent parentStudent);
    ParentStudent getParentStudentById(Long id);
    List<ParentStudent> getParentStudentsByParent(Long parentId);
    List<ParentStudent> getParentStudentsByStudent(Long studentId);
    void deleteParentStudent(Long id);
    boolean relationshipExists(Long parentId, Long studentId);
}