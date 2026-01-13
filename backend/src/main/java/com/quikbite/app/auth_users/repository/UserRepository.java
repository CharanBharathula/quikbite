package com.quikbite.app.auth_users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quikbite.app.auth_users.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
