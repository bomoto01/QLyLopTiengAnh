package com.tam.education_management.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String fullName;

    private String avatarUrl;

    private String status;
}