package com.ansar.sowtfinal.dto.response;

import java.time.Instant;

public record GradeResponse(
        Long id,
        Long studentProfileId,
        Long courseId,
        Integer value,
        String comment,
        Instant gradedAt
) {}
