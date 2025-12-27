package com.ansar.sowtfinal.dto.response;

import com.ansar.sowtfinal.entity.Role;
import com.ansar.sowtfinal.entity.UserStatus;

public class ProfileResponse {
    public Long id;
    public String email;
    public String fullName;
    public Role role;
    public UserStatus status;
}
