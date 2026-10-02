package com.tam.education_management.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tam.education_management.user.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}