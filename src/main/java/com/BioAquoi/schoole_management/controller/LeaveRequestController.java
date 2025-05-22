package com.BioAquoi.schoole_management.controller;

import com.BioAquoi.schoole_management.dto.LeaveRequestDto;
import com.BioAquoi.schoole_management.entity.LeaveRequest;
import com.BioAquoi.schoole_management.service.LeaveRequestService;
import com.BioAquoi.schoole_management.mapper.LeaveRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;
    private final LeaveRequestMapper leaveRequestMapper;

    @PostMapping
    public ResponseEntity<LeaveRequestDto> createLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto) {
        return ResponseEntity.ok(
                leaveRequestMapper.toDto(
                        leaveRequestService.createLeaveRequest(
                                leaveRequestMapper.toEntity(leaveRequestDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> updateLeaveRequest(
            @PathVariable Long id, 
            @RequestBody LeaveRequestDto leaveRequestDto) {
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestById(id);
        leaveRequestMapper.updateEntityFromDto(leaveRequest, leaveRequestDto);
        return ResponseEntity.ok(
                leaveRequestMapper.toDto(leaveRequestService.updateLeaveRequest(leaveRequest))
        );
    }

    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<LeaveRequestDto> updateLeaveRequestStatus(
            @PathVariable Long id, 
            @PathVariable String status) {
        return ResponseEntity.ok(
                leaveRequestMapper.toDto(leaveRequestService.updateLeaveRequestStatus(id, status))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> getLeaveRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(
                leaveRequestMapper.toDto(leaveRequestService.getLeaveRequestById(id))
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LeaveRequestDto>> getLeaveRequestsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(
                leaveRequestMapper.toDtoList(leaveRequestService.getLeaveRequestsByUser(userId))
        );
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LeaveRequestDto>> getLeaveRequestsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(
                leaveRequestMapper.toDtoList(leaveRequestService.getLeaveRequestsByStatus(status))
        );
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<LeaveRequestDto>> getLeaveRequestsBetweenDates(
            @RequestParam LocalDate start, 
            @RequestParam LocalDate end) {
        return ResponseEntity.ok(
                leaveRequestMapper.toDtoList(leaveRequestService.getLeaveRequestsBetweenDates(start, end))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return ResponseEntity.noContent().build();
    }
}