package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.ParentDto;
import com.BioAquoi.schoole_management.entity.Parent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParentMapper {
    
    @Mapping(source = "user.id", target = "userId")
    ParentDto toDto(Parent parent);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "students", ignore = true)
    Parent toEntity(ParentDto parentDto);
    
    List<ParentDto> toDtoList(List<Parent> parents);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "students", ignore = true)
    void updateEntityFromDto(@MappingTarget Parent parent, ParentDto parentDto);
}
