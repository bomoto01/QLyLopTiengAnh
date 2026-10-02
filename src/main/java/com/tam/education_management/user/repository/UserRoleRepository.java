package com.tam.education_management.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tam.education_management.user.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRole.UserRoleId> {
}