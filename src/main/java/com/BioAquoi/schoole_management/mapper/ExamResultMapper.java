package com.BioAquoi.schoole_management.mapper;

import com.BioAquoi.schoole_management.dto.ExamResultDto;
import com.BioAquoi.schoole_management.entity.ExamResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamResultMapper {
    
    @Mapping(source = "exam.id", target = "examId")
    @Mapping(source = "student.id", target = "studentId")
    ExamResultDto toDto(ExamResult examResult);
    
    @Mapping(target = "exam", ignore = true)
    @Mapping(target = "student", ignore = true)
    ExamResult toEntity(ExamResultDto examResultDto);
    
    List<ExamResultDto> toDtoList(List<ExamResult> examResults);
    
    @Mapping(target = "exam", ignore = true)
    @Mapping(target = "student", ignore = true)
    void updateEntityFromDto(@MappingTarget ExamResult examResult, ExamResultDto examResultDto);
}
