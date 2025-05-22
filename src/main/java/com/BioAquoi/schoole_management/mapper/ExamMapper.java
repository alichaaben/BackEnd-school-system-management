package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.ExamDto;
import com.BioAquoi.schoole_management.entity.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamMapper {
    
    @Mapping(source = "subject.id", target = "subjectId")
    ExamDto toDto(Exam exam);
    
    @Mapping(target = "subject", ignore = true)
    Exam toEntity(ExamDto examDto);
    
    List<ExamDto> toDtoList(List<Exam> exams);
    
    @Mapping(target = "subject", ignore = true)
    void updateEntityFromDto(@MappingTarget Exam exam, ExamDto examDto);
}
