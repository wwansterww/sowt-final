package com.ansar.sowtfinal;

import com.ansar.sowtfinal.dto.request.StudentProfileCreateRequest;
import com.ansar.sowtfinal.entity.StudentProfile;
import com.ansar.sowtfinal.entity.User;
import com.ansar.sowtfinal.mapper.StudentProfileMapper;
import com.ansar.sowtfinal.repository.StudentProfileRepository;
import com.ansar.sowtfinal.repository.UserRepository;
import com.ansar.sowtfinal.service.StudentProfileService;
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
class StudentProfileServiceTest {

    @Mock StudentProfileRepository studentProfileRepository;
    @Mock UserRepository userRepository;
    @Mock StudentProfileMapper mapper;

    @InjectMocks
    StudentProfileService service;

    @Test
    void create_shouldLinkUserAndSave() {
        User user = new User();
        user.setId(10L);

        when(userRepository.findById(10L)).thenReturn(Optional.of(user));
        when(studentProfileRepository.save(any(StudentProfile.class))).thenAnswer(inv -> inv.getArgument(0));
        when(mapper.toResponse(any(StudentProfile.class))).thenCallRealMethod(); // если не получится — убери строку и просто verify save()

        StudentProfileCreateRequest req = new StudentProfileCreateRequest();
        req.setUserId(10L);

        service.create(req);

        ArgumentCaptor<StudentProfile> captor = ArgumentCaptor.forClass(StudentProfile.class);
        verify(studentProfileRepository).save(captor.capture());

        StudentProfile saved = captor.getValue();
        assertNotNull(saved.getUser());
        assertEquals(10L, saved.getUser().getId());
    }
}
