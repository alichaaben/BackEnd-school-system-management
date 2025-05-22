package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.*;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.*;
import com.BioAquoi.schoole_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final SchoolRepo schoolRepo;
    private final ClassDivisionRepo classDivisionRepo;
    private final SectionRepo sectionRepo;

    @Override
    public Student createStudent(Student student) {
        if (studentRepo.existsByUserId(student.getUser().getId())) {
            throw new DuplicateEntityException("Student with this user already exists");
        }
        return studentRepo.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        Student existingStudent = getStudentById(student.getId());
        // Add update logic here
        return studentRepo.save(existingStudent);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public List<Student> getStudentsBySchool(Long schoolId) {
        School school = schoolRepo.findById(schoolId)
                .orElseThrow(() -> new EntityNotFoundException("School not found with ID: " + schoolId));
        return studentRepo.findBySchoolId(school.getId());
    }

    @Override
    public List<Student> getStudentsByClassDivision(Long classDivisionId) {
        ClassDivision classDivision = classDivisionRepo.findById(classDivisionId)
                .orElseThrow(() -> new EntityNotFoundException("Class division not found with ID: " + classDivisionId));
        return studentRepo.findByClassDivisionId(classDivision.getId());
    }

    @Override
    public List<Student> getStudentsBySection(Long sectionId) {
        Section section = sectionRepo.findById(sectionId)
                .orElseThrow(() -> new EntityNotFoundException("Section not found with ID: " + sectionId));
        return studentRepo.findBySectionId(section.getId());
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepo.delete(student);
    }

    @Override
    public boolean studentUserExists(Long userId) {
        return studentRepo.existsByUserId(userId);
    }
}