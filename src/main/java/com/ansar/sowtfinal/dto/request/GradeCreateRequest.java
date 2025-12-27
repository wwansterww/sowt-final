package com.ansar.sowtfinal.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record GradeCreateRequest(
        @NotNull Long studentProfileId,
        @NotNull Long courseId,
        @NotNull @Min(0) @Max(100) Integer value,
        String comment
) {}
