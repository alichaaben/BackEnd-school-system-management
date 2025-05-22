package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.ClassDivisionDto;
import com.BioAquoi.schoole_management.entity.ClassDivision;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassDivisionMapper {
    
    @Mapping(source = "school.id", target = "schoolId")
    ClassDivisionDto toDto(ClassDivision classDivision);
    
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    ClassDivision toEntity(ClassDivisionDto classDivisionDto);
    
    List<ClassDivisionDto> toDtoList(List<ClassDivision> classDivisions);
    
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    void updateEntityFromDto(@MappingTarget ClassDivision classDivision, ClassDivisionDto classDivisionDto);
}
