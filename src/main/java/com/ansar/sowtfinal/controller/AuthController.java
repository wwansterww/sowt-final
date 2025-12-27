package com.ansar.sowtfinal.controller;

import com.ansar.sowtfinal.dto.request.ChangePasswordRequest;
import com.ansar.sowtfinal.dto.request.RegisterRequest;
import com.ansar.sowtfinal.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/login")
    public String login(Authentication auth) {
        return "Logged in as: " + auth.getName();
    }


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PutMapping("/change-password")
    public String changePassword(Authentication auth, @Valid @RequestBody ChangePasswordRequest req) {
        authService.changePassword(auth, req);
        return "Password changed";
    }

}
