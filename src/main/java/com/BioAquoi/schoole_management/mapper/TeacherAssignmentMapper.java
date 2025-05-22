package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.TeacherAssignmentDto;
import com.BioAquoi.schoole_management.entity.TeacherAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherAssignmentMapper {
    
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "classDivision.id", target = "classDivisionId")
    @Mapping(source = "section.id", target = "sectionId")
    TeacherAssignmentDto toDto(TeacherAssignment assignment);
    
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "classDivision", ignore = true)
    @Mapping(target = "section", ignore = true)
    TeacherAssignment toEntity(TeacherAssignmentDto assignmentDto);
    
    List<TeacherAssignmentDto> toDtoList(List<TeacherAssignment> assignments);
    
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "classDivision", ignore = true)
    @Mapping(target = "section", ignore = true)
    void updateEntityFromDto(@MappingTarget TeacherAssignment assignment, TeacherAssignmentDto assignmentDto);
}
