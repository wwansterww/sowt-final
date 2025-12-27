package com.ansar.sowtfinal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseCreateRequest(
        @NotBlank String code,
        @NotBlank String title,
        String description,
        @NotNull Integer credits
) {}
