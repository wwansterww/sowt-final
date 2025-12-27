package com.ansar.sowtfinal.service;

import com.ansar.sowtfinal.dto.request.StudentProfileCreateRequest;
import com.ansar.sowtfinal.dto.response.StudentProfileResponse;
import com.ansar.sowtfinal.entity.StudentProfile;
import com.ansar.sowtfinal.entity.User;
import com.ansar.sowtfinal.mapper.StudentProfileMapper;
import com.ansar.sowtfinal.repository.StudentProfileRepository;
import com.ansar.sowtfinal.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;
    private final StudentProfileMapper mapper;

    public StudentProfileService(StudentProfileRepository studentProfileRepository,
                                 UserRepository userRepository,
                                 StudentProfileMapper mapper) {
        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public StudentProfileResponse create(StudentProfileCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User not found: " + request.getUserId()));

        if (studentProfileRepository.existsByUserId(user.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Student profile already exists for userId: " + user.getId());
        }

        StudentProfile profile = StudentProfile.builder()
                .user(user)
                .studentNumber(request.getStudentNumber())
                .major(request.getMajor())
                .studyYear(request.getStudyYear())
                .build();


        StudentProfile saved = studentProfileRepository.save(profile);
        return mapper.toResponse(saved);
    }

    public StudentProfileResponse getById(Long id) {
        StudentProfile profile = studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "StudentProfile not found: " + id));
        return mapper.toResponse(profile);
    }

    public StudentProfileResponse getByUserId(Long userId) {
        StudentProfile profile = studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "StudentProfile not found for userId: " + userId));
        return mapper.toResponse(profile);
    }

    public void delete(Long id) {
        if (!studentProfileRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentProfile not found: " + id);
        }
        studentProfileRepository.deleteById(id);
    }
}
