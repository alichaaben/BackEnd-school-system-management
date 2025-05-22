package com.BioAquoi.schoole_management.service;


import com.BioAquoi.schoole_management.entity.Student;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    List<Student> getStudentsBySchool(Long schoolId);
    List<Student> getStudentsByClassDivision(Long classDivisionId);
    List<Student> getStudentsBySection(Long sectionId);
    void deleteStudent(Long id);
    boolean studentUserExists(Long userId);
}