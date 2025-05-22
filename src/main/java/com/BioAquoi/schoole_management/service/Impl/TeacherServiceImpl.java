package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.*;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.*;
import com.BioAquoi.schoole_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final SchoolRepo schoolRepo;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        if (teacherRepo.existsByUserId(teacher.getUser().getId())) {
            throw new DuplicateEntityException("Teacher with this user already exists");
        }
        return teacherRepo.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        Teacher existingTeacher = getTeacherById(teacher.getId());
        // Add update logic here
        return teacherRepo.save(existingTeacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + id));
    }

    @Override
    public Teacher getTeacherByUserId(Long userId) {
        return teacherRepo.findByUserId(userId);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    @Override
    public List<Teacher> getTeachersBySchool(Long schoolId) {
        School school = schoolRepo.findById(schoolId)
                .orElseThrow(() -> new EntityNotFoundException("School not found with ID: " + schoolId));
        return teacherRepo.findBySchoolId(school.getId());
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacher = getTeacherById(id);
        teacherRepo.delete(teacher);
    }

    @Override
    public boolean teacherUserExists(Long userId) {
        return teacherRepo.existsByUserId(userId);
    }
}
