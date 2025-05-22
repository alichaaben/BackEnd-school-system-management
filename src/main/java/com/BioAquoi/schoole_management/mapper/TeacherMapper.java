package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.TeacherDto;
import com.BioAquoi.schoole_management.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "school.id", target = "schoolId")
    TeacherDto toDto(Teacher teacher);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    Teacher toEntity(TeacherDto teacherDto);
    
    List<TeacherDto> toDtoList(List<Teacher> teachers);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    void updateEntityFromDto(@MappingTarget Teacher teacher, TeacherDto teacherDto);
}
