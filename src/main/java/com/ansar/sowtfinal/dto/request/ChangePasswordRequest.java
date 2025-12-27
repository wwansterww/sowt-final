package com.ansar.sowtfinal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotBlank String oldPassword,
        @Size(min = 6) @NotBlank String newPassword
) {}
