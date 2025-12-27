package com.ansar.sowtfinal.dto.response;

import com.ansar.sowtfinal.entity.Role;
import com.ansar.sowtfinal.entity.UserStatus;

import java.time.Instant;

public record UserResponse(
        Long id,
        String email,
        String fullName,
        Role role,
        UserStatus status,
        Instant createdAt
) {}
