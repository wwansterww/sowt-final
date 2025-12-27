package com.ansar.sowtfinal;

import com.ansar.sowtfinal.dto.request.UpdateProfileRequest;
import com.ansar.sowtfinal.entity.User;
import com.ansar.sowtfinal.repository.UserRepository;
import com.ansar.sowtfinal.service.ProfileService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileServiceTest {

    @Test
    void updateMyProfile_updatesFullName() {
        UserRepository repo = mock(UserRepository.class);
        ProfileService service = new ProfileService(repo);

        User u = new User();
        u.setEmail("ansar@gmail.com");
        u.setFullName("Old");

        when(repo.findByEmail("ansar@gmail.com")).thenReturn(Optional.of(u));

        UpdateProfileRequest req = new UpdateProfileRequest();
        req.fullName = "New Name";

        var res = service.updateMyProfile("ansar@gmail.com", req);

        assertEquals("New Name", res.fullName);
        verify(repo).save(u);
    }
}
