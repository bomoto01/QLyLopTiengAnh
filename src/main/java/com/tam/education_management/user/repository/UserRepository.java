package com.tam.education_management.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tam.education_management.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}