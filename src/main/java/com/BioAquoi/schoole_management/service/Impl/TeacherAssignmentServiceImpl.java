package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.*;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.*;
import com.BioAquoi.schoole_management.service.TeacherAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherAssignmentServiceImpl implements TeacherAssignmentService {

    private final TeacherAssignmentRepo assignmentRepo;
    private final TeacherRepo teacherRepo;
    private final SubjectRepo subjectRepo;
    private final ClassDivisionRepo classDivisionRepo;
    private final SectionRepo sectionRepo;

    @Override
    public TeacherAssignment createAssignment(TeacherAssignment assignment) {
        if (assignmentRepo.existsByTeacherIdAndSubjectIdAndClassDivisionIdAndSectionId(
                assignment.getTeacher().getId(),
                assignment.getSubject().getId(),
                assignment.getClassDivision().getId(),
                assignment.getSection().getId())) {
            throw new DuplicateEntityException("This assignment already exists");
        }
        return assignmentRepo.save(assignment);
    }

    @Override
    public TeacherAssignment updateAssignment(TeacherAssignment assignment) {
        TeacherAssignment existingAssignment = getAssignmentById(assignment.getId());
        // Add update logic here
        return assignmentRepo.save(existingAssignment);
    }

    @Override
    public TeacherAssignment getAssignmentById(Long id) {
        return assignmentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found with ID: " + id));
    }

    @Override
    public List<TeacherAssignment> getAssignmentsByTeacher(Long teacherId) {
        Teacher teacher = teacherRepo.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + teacherId));
        return assignmentRepo.findByTeacherId(teacher.getId());
    }

    @Override
    public List<TeacherAssignment> getAssignmentsBySubject(Long subjectId) {
        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with ID: " + subjectId));
        return assignmentRepo.findBySubjectId(subject.getId());
    }

    @Override
    public List<TeacherAssignment> getAssignmentsByClassDivision(Long classDivisionId) {
        ClassDivision classDivision = classDivisionRepo.findById(classDivisionId)
                .orElseThrow(() -> new EntityNotFoundException("Class division not found with ID: " + classDivisionId));
        return assignmentRepo.findByClassDivisionId(classDivision.getId());
    }

    @Override
    public List<TeacherAssignment> getAssignmentsBySection(Long sectionId) {
        Section section = sectionRepo.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found with ID: " + sectionId));
        return assignmentRepo.findBySectionId(section.getId());
    }

    @Override
    public void deleteAssignment(Long id) {
        TeacherAssignment assignment = getAssignmentById(id);
        assignmentRepo.delete(assignment);
    }

    @Override
    public boolean assignmentExists(Long teacherId, Long subjectId, Long classDivisionId, Long sectionId) {
        return assignmentRepo.existsByTeacherIdAndSubjectIdAndClassDivisionIdAndSectionId(
                teacherId, subjectId, classDivisionId, sectionId);
    }
}
