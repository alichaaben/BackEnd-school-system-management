package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.TeacherAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherAssignmentRepo extends JpaRepository<TeacherAssignment, Long> {
    List<TeacherAssignment> findByTeacherId(Long teacherId);
    List<TeacherAssignment> findBySubjectId(Long subjectId);
    List<TeacherAssignment> findByClassDivisionId(Long classDivisionId);
    List<TeacherAssignment> findBySectionId(Long sectionId);
    boolean existsByTeacherIdAndSubjectIdAndClassDivisionIdAndSectionId(
        Long teacherId, Long subjectId, Long classDivisionId, Long sectionId);
}