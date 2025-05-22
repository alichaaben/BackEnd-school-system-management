package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.ClassDivision;
import com.BioAquoi.schoole_management.entity.Subject;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.ClassDivisionRepo;
import com.BioAquoi.schoole_management.repository.SubjectRepo;
import com.BioAquoi.schoole_management.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepo subjectRepo;
    private final ClassDivisionRepo classDivisionRepo;

    @Override
    public Subject createSubject(Subject subject) {
        if (subjectRepo.existsByNameAndClassDivisionId(
                subject.getName(), 
                subject.getClassDivision().getId())) {
            throw new DuplicateEntityException("Subject with this name already exists in this class division");
        }
        return subjectRepo.save(subject);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        Subject existingSubject = getSubjectById(subject.getId());
        // Add update logic here
        return subjectRepo.save(existingSubject);
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with ID: " + id));
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepo.findAll();
    }

    @Override
    public List<Subject> getSubjectsByClassDivision(Long classDivisionId) {
        ClassDivision classDivision = classDivisionRepo.findById(classDivisionId)
                .orElseThrow(() -> new EntityNotFoundException("Class division not found with ID: " + classDivisionId));
        return subjectRepo.findByClassDivisionId(classDivision.getId());
    }

    @Override
    public void deleteSubject(Long id) {
        Subject subject = getSubjectById(id);
        subjectRepo.delete(subject);
    }

    @Override
    public boolean subjectNameExists(String name, Long classDivisionId) {
        return subjectRepo.existsByNameAndClassDivisionId(name, classDivisionId);
    }
}
