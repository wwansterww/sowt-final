package com.ansar.sowtfinal.controller;

import com.ansar.sowtfinal.dto.request.TeacherProfileCreateRequest;
import com.ansar.sowtfinal.dto.response.TeacherProfileResponse;
import com.ansar.sowtfinal.service.TeacherProfileService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher-profiles")
public class TeacherProfileController {

    private final TeacherProfileService service;

    public TeacherProfileController(TeacherProfileService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TeacherProfileResponse create(@Valid @RequestBody TeacherProfileCreateRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public TeacherProfileResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/by-user/{userId}")
    public TeacherProfileResponse getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
