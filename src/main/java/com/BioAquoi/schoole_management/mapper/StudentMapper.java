package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.StudentDto;
import com.BioAquoi.schoole_management.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "school.id", target = "schoolId")
    @Mapping(source = "classDivision.id", target = "classDivisionId")
    @Mapping(source = "section.id", target = "sectionId")
    StudentDto toDto(Student student);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "classDivision", ignore = true)
    @Mapping(target = "section", ignore = true)
    Student toEntity(StudentDto studentDto);
    
    List<StudentDto> toDtoList(List<Student> students);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "classDivision", ignore = true)
    @Mapping(target = "section", ignore = true)
    void updateEntityFromDto(@MappingTarget Student student, StudentDto studentDto);
}
