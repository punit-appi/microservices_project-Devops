package com.sak.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sak.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
