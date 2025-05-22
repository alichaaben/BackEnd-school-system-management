package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.ScholarshipDto;
import com.BioAquoi.schoole_management.entity.Scholarship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScholarshipMapper {
    
    @Mapping(source = "student.id", target = "studentId")
    ScholarshipDto toDto(Scholarship scholarship);
    
    @Mapping(target = "student", ignore = true)
    Scholarship toEntity(ScholarshipDto scholarshipDto);
    
    List<ScholarshipDto> toDtoList(List<Scholarship> scholarships);
    
    @Mapping(target = "student", ignore = true)
    void updateEntityFromDto(@MappingTarget Scholarship scholarship, ScholarshipDto scholarshipDto);
}
