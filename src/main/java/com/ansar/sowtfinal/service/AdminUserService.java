package com.ansar.sowtfinal.service;

import com.ansar.sowtfinal.dto.request.UserCreateRequest;
import com.ansar.sowtfinal.dto.response.UserResponse;
import com.ansar.sowtfinal.entity.User;
import com.ansar.sowtfinal.entity.UserStatus;
import com.ansar.sowtfinal.mapper.UserMapper;
import com.ansar.sowtfinal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public AdminUserService(UserRepository userRepository,
                            PasswordEncoder passwordEncoder,
                            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setStatus(UserStatus.ACTIVE);

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    public UserResponse blockUser(Long id) {
        User user = getUser(id);
        user.setStatus(UserStatus.BLOCKED);
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse unblockUser(Long id) {
        User user = getUser(id);
        user.setStatus(UserStatus.ACTIVE);
        return userMapper.toResponse(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found: " + id);
        }
        userRepository.deleteById(id);
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }
}
