package com.quikbite.app.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quikbite.app.order.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
