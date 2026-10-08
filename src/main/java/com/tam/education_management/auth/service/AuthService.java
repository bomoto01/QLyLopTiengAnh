package com.tam.education_management.auth.service;

import com.tam.education_management.auth.dto.RegisterRequest;
import com.tam.education_management.user.dto.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);
}