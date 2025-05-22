package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.CourseDto;
import com.BioAquoi.schoole_management.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    
    @Mapping(source = "classDivision.id", target = "classDivisionId")
    @Mapping(source = "subject.id", target = "subjectId")
    CourseDto toDto(Course course);
    
    @Mapping(target = "classDivision", ignore = true)
    @Mapping(target = "subject", ignore = true)
    Course toEntity(CourseDto courseDto);
    
    List<CourseDto> toDtoList(List<Course> courses);
    
    @Mapping(target = "classDivision", ignore = true)
    @Mapping(target = "subject", ignore = true)
    void updateEntityFromDto(@MappingTarget Course course, CourseDto courseDto);
}
