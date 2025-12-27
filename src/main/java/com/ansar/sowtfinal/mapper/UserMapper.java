package com.ansar.sowtfinal.mapper;

import com.ansar.sowtfinal.dto.request.UserCreateRequest;
import com.ansar.sowtfinal.dto.response.UserResponse;
import com.ansar.sowtfinal.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(UserCreateRequest request);

    UserResponse toResponse(User user);
}
