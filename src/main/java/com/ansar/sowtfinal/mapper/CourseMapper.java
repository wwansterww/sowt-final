package com.ansar.sowtfinal.mapper;

import com.ansar.sowtfinal.dto.response.CourseResponse;
import com.ansar.sowtfinal.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "teacherProfileId", source = "teacher.id")
    CourseResponse toResponse(Course course);
}
