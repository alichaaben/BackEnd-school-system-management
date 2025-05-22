package com.BioAquoi.schoole_management.service.Impl;


import com.BioAquoi.schoole_management.entity.*;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.*;
import com.BioAquoi.schoole_management.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepo attendanceRepo;
    private final StudentRepo studentRepo;

    @Override
    public Attendance createAttendance(Attendance attendance) {
        if (attendanceRepo.existsByStudentIdAndDate(
                attendance.getStudent().getId(), 
                attendance.getDate())) {
            throw new DuplicateEntityException("Attendance record already exists for this student on this date");
        }
        return attendanceRepo.save(attendance);
    }

    @Override
    public Attendance updateAttendance(Attendance attendance) {
        Attendance existingAttendance = getAttendanceById(attendance.getId());
        // Add update logic here
        return attendanceRepo.save(existingAttendance);
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found with ID: " + id));
    }

    @Override
    public List<Attendance> getAttendanceByStudent(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        return attendanceRepo.findByStudentId(student.getId());
    }

    @Override
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepo.findByDate(date);
    }

    @Override
    public List<Attendance> getAttendanceByClassAndDate(Long classDivisionId, LocalDate date) {
        return attendanceRepo.findByStudentClassDivisionIdAndDate(classDivisionId, date);
    }

    @Override
    public void deleteAttendance(Long id) {
        Attendance attendance = getAttendanceById(id);
        attendanceRepo.delete(attendance);
    }

    @Override
    public boolean attendanceRecordExists(Long studentId, LocalDate date) {
        return attendanceRepo.existsByStudentIdAndDate(studentId, date);
    }
}
