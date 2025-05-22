package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.*;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.*;
import com.BioAquoi.schoole_management.service.ParentStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentStudentServiceImpl implements ParentStudentService {

    private final ParentStudentRepo parentStudentRepo;
    private final ParentRepo parentRepo;
    private final StudentRepo studentRepo;

    @Override
    public ParentStudent createParentStudent(ParentStudent parentStudent) {
        if (parentStudentRepo.existsByParentIdAndStudentId(
                parentStudent.getParent().getId(),
                parentStudent.getStudent().getId())) {
            throw new DuplicateEntityException("Parent-student relationship already exists");
        }
        return parentStudentRepo.save(parentStudent);
    }

    @Override
    public ParentStudent updateParentStudent(ParentStudent parentStudent) {
        ParentStudent existingRelationship = getParentStudentById(parentStudent.getId());
        // Add update logic here
        return parentStudentRepo.save(existingRelationship);
    }

    @Override
    public ParentStudent getParentStudentById(Long id) {
        return parentStudentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parent-student relationship not found with ID: " + id));
    }

    @Override
    public List<ParentStudent> getParentStudentsByParent(Long parentId) {
        Parent parent = parentRepo.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found with ID: " + parentId));
        return parentStudentRepo.findByParentId(parent.getId());
    }

    @Override
    public List<ParentStudent> getParentStudentsByStudent(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        return parentStudentRepo.findByStudentId(student.getId());
    }

    @Override
    public void deleteParentStudent(Long id) {
        ParentStudent parentStudent = getParentStudentById(id);
        parentStudentRepo.delete(parentStudent);
    }

    @Override
    public boolean relationshipExists(Long parentId, Long studentId) {
        return parentStudentRepo.existsByParentIdAndStudentId(parentId, studentId);
    }
}