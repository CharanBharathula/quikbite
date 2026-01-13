package com.quikbite.app.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quikbite.app.auth_users.model.User;
import com.quikbite.app.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<User> findUserById(Long userId);
}
