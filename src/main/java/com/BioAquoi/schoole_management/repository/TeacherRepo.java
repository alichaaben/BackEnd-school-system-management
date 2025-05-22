package com.BioAquoi.schoole_management.repository;

import com.BioAquoi.schoole_management.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    List<Teacher> findBySchoolId(Long schoolId);
    boolean existsByUserId(Long userId);
    Teacher findByUserId(Long userId);
}