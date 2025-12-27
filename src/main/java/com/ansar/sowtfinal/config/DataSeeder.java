package com.ansar.sowtfinal.config;

import com.ansar.sowtfinal.entity.Role;
import com.ansar.sowtfinal.entity.User;
import com.ansar.sowtfinal.entity.UserStatus;
import com.ansar.sowtfinal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.existsByEmail("admin@university.kz")) return;

        User admin = User.builder()
                .email("admin@university.kz")
                .password(passwordEncoder.encode("Admin12345"))
                .fullName("System Administrator")
                .role(Role.ADMIN)
                .status(UserStatus.ACTIVE)
                .createdAt(Instant.now())
                .build();

        userRepository.save(admin);
    }
}
