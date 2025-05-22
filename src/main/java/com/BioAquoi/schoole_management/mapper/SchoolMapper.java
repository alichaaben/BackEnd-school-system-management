package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.SchoolDto;
import com.BioAquoi.schoole_management.entity.School;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchoolMapper {
    
    SchoolDto toDto(School school);

    @Mapping(target = "classDivisions", ignore = true)
    School toEntity(SchoolDto schoolDto);

    List<SchoolDto> toDtoList(List<School> schools);

    @Mapping(target = "classDivisions", ignore = true)
    void updateEntityFromDto(@MappingTarget School school, SchoolDto schoolDto);
}