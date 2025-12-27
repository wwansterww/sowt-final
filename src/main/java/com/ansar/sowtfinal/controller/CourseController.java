package com.ansar.sowtfinal.controller;

import com.ansar.sowtfinal.dto.request.CourseCreateRequest;
import com.ansar.sowtfinal.dto.response.CourseResponse;
import com.ansar.sowtfinal.mapper.CourseMapper;
import com.ansar.sowtfinal.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public CourseResponse create(Authentication auth, @Valid @RequestBody CourseCreateRequest req) {
        return courseMapper.toResponse(courseService.createCourse(auth, req));
    }
}
