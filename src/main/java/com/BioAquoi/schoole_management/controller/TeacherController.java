package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.TeacherDto;
import com.BioAquoi.schoole_management.entity.Teacher;
import com.BioAquoi.schoole_management.service.TeacherService;
import com.BioAquoi.schoole_management.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody TeacherDto teacherDto) {
        return ResponseEntity.ok(
                teacherMapper.toDto(
                        teacherService.createTeacher(
                                teacherMapper.toEntity(teacherDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(
            @PathVariable Long id, 
            @RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherService.getTeacherById(id);
        teacherMapper.updateEntityFromDto(teacher, teacherDto);
        return ResponseEntity.ok(
                teacherMapper.toDto(teacherService.updateTeacher(teacher))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(
                teacherMapper.toDto(teacherService.getTeacherById(id))
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<TeacherDto> getTeacherByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(
                teacherMapper.toDto(teacherService.getTeacherByUserId(userId))
        );
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        return ResponseEntity.ok(
                teacherMapper.toDtoList(teacherService.getAllTeachers())
        );
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<TeacherDto>> getTeachersBySchool(@PathVariable Long schoolId) {
        return ResponseEntity.ok(
                teacherMapper.toDtoList(teacherService.getTeachersBySchool(schoolId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
