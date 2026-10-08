package com.tam.education_management.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tam.education_management.auth.dto.RegisterRequest;
import com.tam.education_management.common.exception.BadRequestException;
import com.tam.education_management.common.exception.ResourceNotFoundException;
import com.tam.education_management.user.dto.UserResponse;
import com.tam.education_management.user.entity.Role;
import com.tam.education_management.user.entity.User;
import com.tam.education_management.user.entity.UserRole;
import com.tam.education_management.user.mapper.UserMapper;
import com.tam.education_management.user.repository.RoleRepository;
import com.tam.education_management.user.repository.UserRepository;
import com.tam.education_management.user.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse register(RegisterRequest request) {

        // 1. Kiểm tra username
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username đã tồn tại");
        }

        // 2. Kiểm tra email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email đã tồn tại");
        }

        // 3. Tạo User
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());

        // 4. Hash password bằng BCrypt
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // 5. Lưu User
        User savedUser = userRepository.save(user);

        // 6. Lấy role STUDENT
        Role studentRole = roleRepository.findByName("STUDENT")
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role STUDENT không tồn tại")
                );

        // 7. Tạo UserRole
        UserRole userRole = UserRole.builder()
                .id(
                        UserRole.UserRoleId.builder()
                                .userId(savedUser.getId())
                                .roleId(studentRole.getId())
                                .build()
                )
                .user(savedUser)
                .role(studentRole)
                .build();

        // 8. Lưu UserRole
        userRoleRepository.save(userRole);

        // 9. Trả response
        return userMapper.toResponse(savedUser);
    }
}