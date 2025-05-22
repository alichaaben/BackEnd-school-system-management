package com.BioAquoi.schoole_management.service.Impl;


import com.BioAquoi.schoole_management.entity.*;
import com.BioAquoi.schoole_management.Exceptions.EntityNotFoundException;
import com.BioAquoi.schoole_management.Exceptions.DuplicateEntityException;
import com.BioAquoi.schoole_management.repository.*;
import com.BioAquoi.schoole_management.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepo staffRepo;
    private final SchoolRepo schoolRepo;

    @Override
    public Staff createStaff(Staff staff) {
        if (staffRepo.existsByUserId(staff.getUser().getId())) {
            throw new DuplicateEntityException("Staff with this user already exists");
        }
        return staffRepo.save(staff);
    }

    @Override
    public Staff updateStaff(Staff staff) {
        Staff existingStaff = getStaffById(staff.getId());
        // Add update logic here
        return staffRepo.save(existingStaff);
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Staff not found with ID: " + id));
    }

    @Override
    public Staff getStaffByUserId(Long userId) {
        return staffRepo.findByUserId(userId);
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepo.findAll();
    }

    @Override
    public List<Staff> getStaffBySchool(Long schoolId) {
        School school = schoolRepo.findById(schoolId)
                .orElseThrow(() -> new EntityNotFoundException("School not found with ID: " + schoolId));
        return staffRepo.findBySchoolId(school.getId());
    }

    @Override
    public void deleteStaff(Long id) {
        Staff staff = getStaffById(id);
        staffRepo.delete(staff);
    }

    @Override
    public boolean staffUserExists(Long userId) {
        return staffRepo.existsByUserId(userId);
    }
}