package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.StaffDto;
import com.BioAquoi.schoole_management.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "school.id", target = "schoolId")
    StaffDto toDto(Staff staff);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "school", ignore = true)
    Staff toEntity(StaffDto staffDto);
    
    List<StaffDto> toDtoList(List<Staff> staffList);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "school", ignore = true)
    void updateEntityFromDto(@MappingTarget Staff staff, StaffDto staffDto);
}