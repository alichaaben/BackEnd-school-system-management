package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.LeaveRequest;
import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestService {
    LeaveRequest createLeaveRequest(LeaveRequest leaveRequest);
    LeaveRequest updateLeaveRequest(LeaveRequest leaveRequest);
    LeaveRequest getLeaveRequestById(Long id);
    List<LeaveRequest> getLeaveRequestsByUser(Long userId);
    List<LeaveRequest> getLeaveRequestsByStatus(String status);
    List<LeaveRequest> getLeaveRequestsBetweenDates(LocalDate start, LocalDate end);
    void deleteLeaveRequest(Long id);
    LeaveRequest updateLeaveRequestStatus(Long id, String status);
}