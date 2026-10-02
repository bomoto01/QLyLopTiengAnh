package com.tam.education_management.user.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tam.education_management.common.response.ApiResponse;
import com.tam.education_management.user.dto.UserCreateRequest;
import com.tam.education_management.user.dto.UserResponse;
import com.tam.education_management.user.dto.UserUpdateRequest;
import com.tam.education_management.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid @RequestBody UserCreateRequest request
    ) {
        UserResponse response = userService.createUser(request);

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                "Tạo user thành công",
                response
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(
            @PathVariable Long id
    ) {
        UserResponse response = userService.getUserById(id);

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Lấy user thành công",
                response
        );

        return ResponseEntity.ok(apiResponse);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> response = userService.getAllUsers();

        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Lấy danh sách user thành công",
                response
        );

        return ResponseEntity.ok(apiResponse);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        UserResponse response = userService.updateUser(id, request);

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Cập nhật user thành công",
                response
        );

        return ResponseEntity.ok(apiResponse);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);

        ApiResponse<Void> apiResponse = new ApiResponse<>(
                LocalDateTime.now(),
                HttpStatus.NO_CONTENT.value(),
                "Xóa user thành công",
                null
        );

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(apiResponse);
    }
}