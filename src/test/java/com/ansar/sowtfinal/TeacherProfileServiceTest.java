package com.ansar.sowtfinal;

import com.ansar.sowtfinal.dto.request.TeacherProfileCreateRequest;
import com.ansar.sowtfinal.entity.TeacherProfile;
import com.ansar.sowtfinal.entity.User;
import com.ansar.sowtfinal.mapper.TeacherProfileMapper;
import com.ansar.sowtfinal.repository.TeacherProfileRepository;
import com.ansar.sowtfinal.repository.UserRepository;
import com.ansar.sowtfinal.service.TeacherProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherProfileServiceTest {

    @Mock TeacherProfileRepository teacherProfileRepository;
    @Mock UserRepository userRepository;
    @Mock TeacherProfileMapper mapper;

    @InjectMocks
    TeacherProfileService service;

    @Test
    void create_shouldLinkUserAndSave() {
        User user = new User();
        user.setId(20L);

        when(userRepository.findById(20L)).thenReturn(Optional.of(user));
        when(teacherProfileRepository.save(any(TeacherProfile.class))).thenAnswer(inv -> inv.getArgument(0));

        TeacherProfileCreateRequest req = new TeacherProfileCreateRequest();
        req.setUserId(20L);

        service.create(req);

        ArgumentCaptor<TeacherProfile> captor = ArgumentCaptor.forClass(TeacherProfile.class);
        verify(teacherProfileRepository).save(captor.capture());

        TeacherProfile saved = captor.getValue();
        assertNotNull(saved.getUser());
        assertEquals(20L, saved.getUser().getId());
    }
}
