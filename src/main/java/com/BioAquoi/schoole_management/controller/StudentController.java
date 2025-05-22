package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.StudentDto;
import com.BioAquoi.schoole_management.entity.Student;
import com.BioAquoi.schoole_management.service.StudentService;
import com.BioAquoi.schoole_management.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(
                studentMapper.toDto(
                        studentService.createStudent(
                                studentMapper.toEntity(studentDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id, 
            @RequestBody StudentDto studentDto) {
        Student student = studentService.getStudentById(id);
        studentMapper.updateEntityFromDto(student, studentDto);
        return ResponseEntity.ok(
                studentMapper.toDto(studentService.updateStudent(student))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(
                studentMapper.toDto(studentService.getStudentById(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(
                studentMapper.toDtoList(studentService.getAllStudents())
        );
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<StudentDto>> getStudentsBySchool(@PathVariable Long schoolId) {
        return ResponseEntity.ok(
                studentMapper.toDtoList(studentService.getStudentsBySchool(schoolId))
        );
    }

    @GetMapping("/class-division/{classDivisionId}")
    public ResponseEntity<List<StudentDto>> getStudentsByClassDivision(@PathVariable Long classDivisionId) {
        return ResponseEntity.ok(
                studentMapper.toDtoList(studentService.getStudentsByClassDivision(classDivisionId))
        );
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<StudentDto>> getStudentsBySection(@PathVariable Long sectionId) {
        return ResponseEntity.ok(
                studentMapper.toDtoList(studentService.getStudentsBySection(sectionId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
