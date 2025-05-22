package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.*;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.*;
import com.BioAquoi.schoole_management.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;
    private final ClassDivisionRepo classDivisionRepo;
    private final SubjectRepo subjectRepo;

    @Override
    public Course createCourse(Course course) {
        if (courseRepo.existsByTitleAndClassDivisionIdAndSubjectId(
                course.getTitle(),
                course.getClassDivision().getId(),
                course.getSubject().getId())) {
            throw new DuplicateEntityException("Course with this title already exists for this class and subject");
        }
        return courseRepo.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        Course existingCourse = getCourseById(course.getId());
        // Add update logic here
        return courseRepo.save(existingCourse);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public List<Course> getCoursesByClassDivision(Long classDivisionId) {
        ClassDivision classDivision = classDivisionRepo.findById(classDivisionId)
                .orElseThrow(() -> new EntityNotFoundException("Class division not found with ID: " + classDivisionId));
        return courseRepo.findByClassDivisionId(classDivision.getId());
    }

    @Override
    public List<Course> getCoursesBySubject(Long subjectId) {
        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with ID: " + subjectId));
        return courseRepo.findBySubjectId(subject.getId());
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepo.delete(course);
    }

    @Override
    public boolean courseExists(String title, Long classDivisionId, Long subjectId) {
        return courseRepo.existsByTitleAndClassDivisionIdAndSubjectId(title, classDivisionId, subjectId);
    }
}
