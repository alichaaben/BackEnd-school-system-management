package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.AttendanceDto;
import com.BioAquoi.schoole_management.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
    
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "recordedBy.id", target = "recordedById")
    AttendanceDto toDto(Attendance attendance);
    
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "recordedBy", ignore = true)
    Attendance toEntity(AttendanceDto attendanceDto);
    
    List<AttendanceDto> toDtoList(List<Attendance> attendances);
    
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "recordedBy", ignore = true)
    void updateEntityFromDto(@MappingTarget Attendance attendance, AttendanceDto attendanceDto);
}
