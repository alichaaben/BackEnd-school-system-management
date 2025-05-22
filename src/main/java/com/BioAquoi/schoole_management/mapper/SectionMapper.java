package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.SectionDto;
import com.BioAquoi.schoole_management.entity.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionMapper {
    
    @Mapping(source = "classDivision.id", target = "classDivisionId")
    SectionDto toDto(Section section);
    
    @Mapping(target = "classDivision", ignore = true)
    Section toEntity(SectionDto sectionDto);
    
    List<SectionDto> toDtoList(List<Section> sections);
    
    @Mapping(target = "classDivision", ignore = true)
    void updateEntityFromDto(@MappingTarget Section section, SectionDto sectionDto);
}
