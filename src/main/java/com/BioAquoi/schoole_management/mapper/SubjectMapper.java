package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.SubjectDto;
import com.BioAquoi.schoole_management.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    
    @Mapping(source = "classDivision.id", target = "classDivisionId")
    SubjectDto toDto(Subject subject);
    
    @Mapping(target = "classDivision", ignore = true)
    Subject toEntity(SubjectDto subjectDto);
    
    List<SubjectDto> toDtoList(List<Subject> subjects);
    
    @Mapping(target = "classDivision", ignore = true)
    void updateEntityFromDto(@MappingTarget Subject subject, SubjectDto subjectDto);
}
