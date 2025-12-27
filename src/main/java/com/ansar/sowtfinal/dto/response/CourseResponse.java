package com.ansar.sowtfinal.dto.response;

public record CourseResponse(
        Long id,
        String code,
        String title,
        String description,
        Integer credits,
        Long teacherProfileId
) {}
