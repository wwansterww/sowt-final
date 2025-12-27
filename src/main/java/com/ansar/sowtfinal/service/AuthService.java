package com.ansar.sowtfinal.service;

import com.ansar.sowtfinal.dto.request.ChangePasswordRequest;
import com.ansar.sowtfinal.dto.request.RegisterRequest;
import com.ansar.sowtfinal.entity.Role;
import com.ansar.sowtfinal.entity.User;
import com.ansar.sowtfinal.entity.UserStatus;
import com.ansar.sowtfinal.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .email(req.email())
                .password(passwordEncoder.encode(req.password()))
                .fullName(req.fullName())
                .role(Role.USER)
                .status(UserStatus.ACTIVE)
                .createdAt(Instant.now())
                .build();

        userRepository.save(user);
        return "Registered успешно. Теперь логин через Basic Auth (email/password).";
    }

    public void changePassword(Authentication auth, ChangePasswordRequest req) {
        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(req.oldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password incorrect");
        }

        user.setPassword(passwordEncoder.encode(req.newPassword()));
        userRepository.save(user);
    }
}
