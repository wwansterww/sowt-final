package com.ansar.sowtfinal.controller;

import com.ansar.sowtfinal.dto.request.ChangePasswordRequest;
import com.ansar.sowtfinal.dto.request.UpdateProfileRequest;
import com.ansar.sowtfinal.dto.response.UserResponse;
import com.ansar.sowtfinal.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public UserResponse me(Authentication auth) {
        return profileService.me(auth);
    }

    @PutMapping("/me")
    public UserResponse update(@Valid @RequestBody UpdateProfileRequest request, Authentication auth) {
        return profileService.update(auth, request);
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid @RequestBody ChangePasswordRequest request, Authentication auth) {
        return profileService.changePassword(auth, request);
    }
}
