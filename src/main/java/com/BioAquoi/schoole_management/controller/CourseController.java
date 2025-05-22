package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.CourseDto;
import com.BioAquoi.schoole_management.entity.Course;
import com.BioAquoi.schoole_management.service.CourseService;
import com.BioAquoi.schoole_management.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(
                courseMapper.toDto(
                        courseService.createCourse(
                                courseMapper.toEntity(courseDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(
            @PathVariable Long id, 
            @RequestBody CourseDto courseDto) {
        Course course = courseService.getCourseById(id);
        courseMapper.updateEntityFromDto(course, courseDto);
        return ResponseEntity.ok(
                courseMapper.toDto(courseService.updateCourse(course))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(
                courseMapper.toDto(courseService.getCourseById(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(
                courseMapper.toDtoList(courseService.getAllCourses())
        );
    }

    @GetMapping("/class/{classDivisionId}")
    public ResponseEntity<List<CourseDto>> getCoursesByClassDivision(@PathVariable Long classDivisionId) {
        return ResponseEntity.ok(
                courseMapper.toDtoList(courseService.getCoursesByClassDivision(classDivisionId))
        );
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<CourseDto>> getCoursesBySubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(
                courseMapper.toDtoList(courseService.getCoursesBySubject(subjectId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}