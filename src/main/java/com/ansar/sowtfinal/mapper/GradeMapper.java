package com.ansar.sowtfinal.mapper;

import com.ansar.sowtfinal.dto.response.GradeResponse;
import com.ansar.sowtfinal.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mapping(target = "studentProfileId", source = "student.id")
    @Mapping(target = "courseId", source = "course.id")
    GradeResponse toResponse(Grade grade);
}
