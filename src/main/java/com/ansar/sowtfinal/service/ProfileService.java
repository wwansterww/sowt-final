package com.ansar.sowtfinal.service;

import com.ansar.sowtfinal.dto.request.ChangePasswordRequest;
import com.ansar.sowtfinal.dto.request.UpdateProfileRequest;
import com.ansar.sowtfinal.dto.response.UserResponse;
import com.ansar.sowtfinal.entity.User;
import com.ansar.sowtfinal.mapper.UserMapper;
import com.ansar.sowtfinal.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public ProfileService(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserResponse me(Authentication auth) {
        User user = getByAuth(auth);
        return userMapper.toResponse(user);
    }

    public UserResponse update(Authentication auth, UpdateProfileRequest request) {
        User user = getByAuth(auth);
        user.setFullName(request.fullName());
        return userMapper.toResponse(userRepository.save(user));
    }

    public String changePassword(Authentication auth, ChangePasswordRequest request) {
        User user = getByAuth(auth);

        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is wrong");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
        return "Password changed";
    }

    private User getByAuth(Authentication auth) {
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + email));
    }
}
