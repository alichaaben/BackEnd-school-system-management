package com.BioAquoi.schoole_management.controller;


import com.BioAquoi.schoole_management.dto.StaffDto;
import com.BioAquoi.schoole_management.entity.Staff;
import com.BioAquoi.schoole_management.service.StaffService;
import com.BioAquoi.schoole_management.mapper.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;
    private final StaffMapper staffMapper;

    @PostMapping
    public ResponseEntity<StaffDto> createStaff(@RequestBody StaffDto staffDto) {
        return ResponseEntity.ok(
                staffMapper.toDto(
                        staffService.createStaff(
                                staffMapper.toEntity(staffDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDto> updateStaff(
            @PathVariable Long id, 
            @RequestBody StaffDto staffDto) {
        Staff staff = staffService.getStaffById(id);
        staffMapper.updateEntityFromDto(staff, staffDto);
        return ResponseEntity.ok(
                staffMapper.toDto(staffService.updateStaff(staff))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable Long id) {
        return ResponseEntity.ok(
                staffMapper.toDto(staffService.getStaffById(id))
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<StaffDto> getStaffByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(
                staffMapper.toDto(staffService.getStaffByUserId(userId))
        );
    }

    @GetMapping
    public ResponseEntity<List<StaffDto>> getAllStaff() {
        return ResponseEntity.ok(
                staffMapper.toDtoList(staffService.getAllStaff())
        );
    }

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<StaffDto>> getStaffBySchool(@PathVariable Long schoolId) {
        return ResponseEntity.ok(
                staffMapper.toDtoList(staffService.getStaffBySchool(schoolId))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}
