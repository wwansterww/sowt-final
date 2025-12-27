package com.ansar.sowtfinal.mapper;

import com.ansar.sowtfinal.dto.response.StudentProfileResponse;
import com.ansar.sowtfinal.entity.StudentProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentProfileMapper {

    @Mapping(target = "userId", source = "user.id")
    StudentProfileResponse toResponse(StudentProfile entity);
}
