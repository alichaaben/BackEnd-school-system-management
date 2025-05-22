package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.ParentStudentDto;
import com.BioAquoi.schoole_management.entity.ParentStudent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParentStudentMapper {
    
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "student.id", target = "studentId")
    ParentStudentDto toDto(ParentStudent parentStudent);
    
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "student", ignore = true)
    ParentStudent toEntity(ParentStudentDto parentStudentDto);
    
    List<ParentStudentDto> toDtoList(List<ParentStudent> parentStudents);
    
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "student", ignore = true)
    void updateEntityFromDto(@MappingTarget ParentStudent parentStudent, ParentStudentDto parentStudentDto);
}
