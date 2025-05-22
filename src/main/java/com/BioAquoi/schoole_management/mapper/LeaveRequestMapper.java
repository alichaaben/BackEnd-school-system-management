package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.LeaveRequestDto;
import com.BioAquoi.schoole_management.entity.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LeaveRequestMapper {
    
    @Mapping(source = "user.id", target = "userId")
    LeaveRequestDto toDto(LeaveRequest leaveRequest);
    
    @Mapping(target = "user", ignore = true)
    LeaveRequest toEntity(LeaveRequestDto leaveRequestDto);
    
    List<LeaveRequestDto> toDtoList(List<LeaveRequest> leaveRequests);
    
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDto(@MappingTarget LeaveRequest leaveRequest, LeaveRequestDto leaveRequestDto);
}