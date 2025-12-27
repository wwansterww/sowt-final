package com.ansar.sowtfinal.controller;

import com.ansar.sowtfinal.dto.request.UserCreateRequest;
import com.ansar.sowtfinal.dto.response.UserResponse;
import com.ansar.sowtfinal.service.AdminUserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserCreateRequest request) {
        return adminUserService.createUser(request);
    }

    @PatchMapping("/{id}/block")
    public UserResponse block(@PathVariable Long id) {
        return adminUserService.blockUser(id);
    }

    @PatchMapping("/{id}/unblock")
    public UserResponse unblock(@PathVariable Long id) {
        return adminUserService.unblockUser(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        adminUserService.deleteUser(id);
        return "Deleted";
    }
}
