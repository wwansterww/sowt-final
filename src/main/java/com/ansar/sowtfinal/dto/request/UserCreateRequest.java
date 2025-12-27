package com.ansar.sowtfinal.dto.request;

import com.ansar.sowtfinal.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @Email @NotBlank String email,
        @Size(min = 6) @NotBlank String password,
        @NotBlank String fullName,
        @NotNull Role role
) {}
