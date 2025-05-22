package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.Teacher;
import java.util.List;

public interface TeacherService {
    Teacher createTeacher(Teacher teacher);
    Teacher updateTeacher(Teacher teacher);
    Teacher getTeacherById(Long id);
    Teacher getTeacherByUserId(Long userId);
    List<Teacher> getAllTeachers();
    List<Teacher> getTeachersBySchool(Long schoolId);
    void deleteTeacher(Long id);
    boolean teacherUserExists(Long userId);
}
