package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findByClassDivisionId(Long classDivisionId);
    List<Course> findBySubjectId(Long subjectId);
    boolean existsByTitleAndClassDivisionIdAndSubjectId(String title, Long classDivisionId, Long subjectId);
}