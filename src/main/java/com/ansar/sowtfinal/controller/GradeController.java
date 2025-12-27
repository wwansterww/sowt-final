package com.ansar.sowtfinal.controller;

import com.ansar.sowtfinal.dto.request.GradeCreateRequest;
import com.ansar.sowtfinal.dto.response.GradeResponse;
import com.ansar.sowtfinal.mapper.GradeMapper;
import com.ansar.sowtfinal.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;
    private final GradeMapper gradeMapper;

    public GradeController(GradeService gradeService, GradeMapper gradeMapper) {
        this.gradeService = gradeService;
        this.gradeMapper = gradeMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public GradeResponse add(@Valid @RequestBody GradeCreateRequest req) {
        return gradeMapper.toResponse(gradeService.addGrade(req));
    }

    @GetMapping("/student/{studentProfileId}")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN','USER')")
    public List<GradeResponse> studentGrades(@PathVariable Long studentProfileId) {
        return gradeService.getStudentGrades(studentProfileId).stream()
                .map(gradeMapper::toResponse)
                .toList();
    }
}
