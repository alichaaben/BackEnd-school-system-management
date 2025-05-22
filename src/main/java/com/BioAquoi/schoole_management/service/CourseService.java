package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.Course;
import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    Course updateCourse(Course course);
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    List<Course> getCoursesByClassDivision(Long classDivisionId);
    List<Course> getCoursesBySubject(Long subjectId);
    void deleteCourse(Long id);
    boolean courseExists(String title, Long classDivisionId, Long subjectId);
}
