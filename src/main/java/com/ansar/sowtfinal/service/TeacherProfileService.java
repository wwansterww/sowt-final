package com.ansar.sowtfinal.service;

import com.ansar.sowtfinal.dto.request.TeacherProfileCreateRequest;
import com.ansar.sowtfinal.dto.response.TeacherProfileResponse;
import com.ansar.sowtfinal.entity.TeacherProfile;
import com.ansar.sowtfinal.entity.User;
import com.ansar.sowtfinal.mapper.TeacherProfileMapper;
import com.ansar.sowtfinal.repository.TeacherProfileRepository;
import com.ansar.sowtfinal.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeacherProfileService {

    private final TeacherProfileRepository teacherProfileRepository;
    private final UserRepository userRepository;
    private final TeacherProfileMapper mapper;

    public TeacherProfileService(TeacherProfileRepository teacherProfileRepository,
                                 UserRepository userRepository,
                                 TeacherProfileMapper mapper) {
        this.teacherProfileRepository = teacherProfileRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public TeacherProfileResponse create(TeacherProfileCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found: " + request.getUserId()
                ));

        TeacherProfile profile = new TeacherProfile();
        profile.setUser(user);
        profile.setDepartment(request.getDepartment());
        profile.setPosition(request.getPosition());

        TeacherProfile saved = teacherProfileRepository.save(profile);
        return mapper.toResponse(saved);
    }

    public TeacherProfileResponse getById(Long id) {
        TeacherProfile profile = teacherProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TeacherProfile not found: " + id));
        return mapper.toResponse(profile);
    }

    public TeacherProfileResponse getByUserId(Long userId) {
        TeacherProfile profile = teacherProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TeacherProfile not found for userId: " + userId));
        return mapper.toResponse(profile);
    }

    public void delete(Long id) {
        if (!teacherProfileRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TeacherProfile not found: " + id);
        }
        teacherProfileRepository.deleteById(id);
    }
}
