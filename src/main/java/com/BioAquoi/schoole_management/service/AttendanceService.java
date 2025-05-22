package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.Attendance;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {
    Attendance createAttendance(Attendance attendance);
    Attendance updateAttendance(Attendance attendance);
    Attendance getAttendanceById(Long id);
    List<Attendance> getAttendanceByStudent(Long studentId);
    List<Attendance> getAttendanceByDate(LocalDate date);
    List<Attendance> getAttendanceByClassAndDate(Long classDivisionId, LocalDate date);
    void deleteAttendance(Long id);
    boolean attendanceRecordExists(Long studentId, LocalDate date);
}
