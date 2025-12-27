package com.ansar.sowtfinal;

import com.ansar.sowtfinal.entity.Role;
import com.ansar.sowtfinal.entity.User;
import com.ansar.sowtfinal.entity.UserStatus;
import com.ansar.sowtfinal.repository.UserRepository;
import com.ansar.sowtfinal.service.AdminUserService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminUserServiceTest {

    @Test
    void blockUser_setsStatusBlocked() {
        UserRepository repo = mock(UserRepository.class);
        AdminUserService service = new AdminUserService(repo);

        User u = new User();
        u.setId(1L);
        u.setEmail("a@b.com");
        u.setRole(Role.USER);
        u.setStatus(UserStatus.ACTIVE);

        when(repo.findById(1L)).thenReturn(Optional.of(u));

        service.blockUser(1L);

        assertEquals(UserStatus.BLOCKED, u.getStatus());
        verify(repo).save(u);
    }

    @Test
    void activateUser_setsStatusActive() {
        UserRepository repo = mock(UserRepository.class);
        AdminUserService service = new AdminUserService(repo);

        User u = new User();
        u.setId(2L);
        u.setStatus(UserStatus.BLOCKED);

        when(repo.findById(2L)).thenReturn(Optional.of(u));

        service.activateUser(2L);

        assertEquals(UserStatus.ACTIVE, u.getStatus());
        verify(repo).save(u);
    }
}
