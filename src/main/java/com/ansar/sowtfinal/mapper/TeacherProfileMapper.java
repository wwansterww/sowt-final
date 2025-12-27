package com.ansar.sowtfinal.mapper;

import com.ansar.sowtfinal.dto.response.TeacherProfileResponse;
import com.ansar.sowtfinal.entity.TeacherProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherProfileMapper {

    @Mapping(target = "userId", expression = "java(entity.getUser() != null ? entity.getUser().getId() : null)")
    TeacherProfileResponse toResponse(TeacherProfile entity);
}
