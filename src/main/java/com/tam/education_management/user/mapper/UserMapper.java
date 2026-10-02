package com.tam.education_management.user.mapper;

import com.tam.education_management.user.dto.UserCreateRequest;
import com.tam.education_management.user.dto.UserResponse;
import com.tam.education_management.user.dto.UserUpdateRequest;
import com.tam.education_management.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserCreateRequest request);

    UserResponse toResponse(User user);

    void updateEntity(
            UserUpdateRequest request,
            @MappingTarget User user
    );
}