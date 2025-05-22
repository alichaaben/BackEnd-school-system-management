package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.AttendanceDto;
import com.BioAquoi.schoole_management.entity.Attendance;
import com.BioAquoi.schoole_management.service.AttendanceService;
import com.BioAquoi.schoole_management.mapper.AttendanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

        private final AttendanceService attendanceService;
        private final AttendanceMapper attendanceMapper;

        @PostMapping
        public ResponseEntity<AttendanceDto> createAttendance(@RequestBody AttendanceDto attendanceDto) {
                return ResponseEntity.ok(
                                attendanceMapper.toDto(
                                                attendanceService.createAttendance(
                                                                attendanceMapper.toEntity(attendanceDto))));
        }

        @PutMapping("/{id}")
        public ResponseEntity<AttendanceDto> updateAttendance(
                        @PathVariable Long id,
                        @RequestBody AttendanceDto attendanceDto) {
                Attendance attendance = attendanceService.getAttendanceById(id);
                attendanceMapper.updateEntityFromDto(attendance, attendanceDto);
                return ResponseEntity.ok(
                                attendanceMapper.toDto(attendanceService.updateAttendance(attendance)));
        }

        @GetMapping("/{id}")
        public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable Long id) {
                return ResponseEntity.ok(
                                attendanceMapper.toDto(attendanceService.getAttendanceById(id)));
        }

        @GetMapping("/student/{studentId}")
        public ResponseEntity<List<AttendanceDto>> getAttendanceByStudent(@PathVariable Long studentId) {
                return ResponseEntity.ok(
                                attendanceMapper.toDtoList(attendanceService.getAttendanceByStudent(studentId)));
        }

        @GetMapping("/date/{date}")
        public ResponseEntity<List<AttendanceDto>> getAttendanceByDate(@PathVariable LocalDate date) {
                return ResponseEntity.ok(
                                attendanceMapper.toDtoList(attendanceService.getAttendanceByDate(date)));
        }

        @GetMapping("/class/{classDivisionId}/date/{date}")
        public ResponseEntity<List<AttendanceDto>> getAttendanceByClassAndDate(
                        @PathVariable Long classDivisionId,
                        @PathVariable LocalDate date) {
                return ResponseEntity.ok(
                                attendanceMapper.toDtoList(
                                                attendanceService.getAttendanceByClassAndDate(classDivisionId, date)));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
                attendanceService.deleteAttendance(id);
                return ResponseEntity.noContent().build();
        }
}