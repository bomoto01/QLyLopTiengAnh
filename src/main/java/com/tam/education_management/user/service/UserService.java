package com.tam.education_management.user.service;

import java.util.List;

import com.tam.education_management.user.dto.UserCreateRequest;
import com.tam.education_management.user.dto.UserResponse;
import com.tam.education_management.user.dto.UserUpdateRequest;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);
}