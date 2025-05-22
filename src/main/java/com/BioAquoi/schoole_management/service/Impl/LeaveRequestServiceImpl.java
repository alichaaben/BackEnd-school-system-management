package com.BioAquoi.schoole_management.service.Impl;

import com.BioAquoi.schoole_management.entity.LeaveRequest;
import com.BioAquoi.schoole_management.entity.User;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.repository.LeaveRequestRepo;
import com.BioAquoi.schoole_management.repository.UserRepo;
import com.BioAquoi.schoole_management.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepo leaveRequestRepo;
    private final UserRepo userRepo;

    @Override
    public LeaveRequest createLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepo.save(leaveRequest);
    }

    @Override
    public LeaveRequest updateLeaveRequest(LeaveRequest leaveRequest) {
        LeaveRequest existingRequest = getLeaveRequestById(leaveRequest.getId());
        // Add update logic here
        return leaveRequestRepo.save(existingRequest);
    }

    @Override
    public LeaveRequest getLeaveRequestById(Long id) {
        return leaveRequestRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leave request not found with ID: " + id));
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        return leaveRequestRepo.findByUserId(user.getId());
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByStatus(String status) {
        return leaveRequestRepo.findByStatus(status);
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsBetweenDates(LocalDate start, LocalDate end) {
        return leaveRequestRepo.findByStartDateBetween(start, end);
    }

    @Override
    public void deleteLeaveRequest(Long id) {
        LeaveRequest leaveRequest = getLeaveRequestById(id);
        leaveRequestRepo.delete(leaveRequest);
    }

    @Override
    public LeaveRequest updateLeaveRequestStatus(Long id, String status) {
        LeaveRequest leaveRequest = getLeaveRequestById(id);
        leaveRequest.setStatus(status);
        return leaveRequestRepo.save(leaveRequest);
    }
}
