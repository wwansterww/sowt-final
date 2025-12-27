package com.ansar.sowtfinal.controller;

import com.ansar.sowtfinal.dto.request.StudentProfileCreateRequest;
import com.ansar.sowtfinal.dto.response.StudentProfileResponse;
import com.ansar.sowtfinal.service.StudentProfileService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-profiles")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public StudentProfileResponse create(@Valid @RequestBody StudentProfileCreateRequest request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    public StudentProfileResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/by-user/{userId}")
    public StudentProfileResponse getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
