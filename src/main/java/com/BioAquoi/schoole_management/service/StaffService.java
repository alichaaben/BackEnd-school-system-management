package com.BioAquoi.schoole_management.service;

import com.BioAquoi.schoole_management.entity.Staff;
import java.util.List;

public interface StaffService {
    Staff createStaff(Staff staff);
    Staff updateStaff(Staff staff);
    Staff getStaffById(Long id);
    Staff getStaffByUserId(Long userId);
    List<Staff> getAllStaff();
    List<Staff> getStaffBySchool(Long schoolId);
    void deleteStaff(Long id);
    boolean staffUserExists(Long userId);
}
