package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.TeacherAssignment;
import java.util.List;

public interface TeacherAssignmentService {
    TeacherAssignment createAssignment(TeacherAssignment assignment);
    TeacherAssignment updateAssignment(TeacherAssignment assignment);
    TeacherAssignment getAssignmentById(Long id);
    List<TeacherAssignment> getAssignmentsByTeacher(Long teacherId);
    List<TeacherAssignment> getAssignmentsBySubject(Long subjectId);
    List<TeacherAssignment> getAssignmentsByClassDivision(Long classDivisionId);
    List<TeacherAssignment> getAssignmentsBySection(Long sectionId);
    void deleteAssignment(Long id);
    boolean assignmentExists(Long teacherId, Long subjectId, Long classDivisionId, Long sectionId);
}
