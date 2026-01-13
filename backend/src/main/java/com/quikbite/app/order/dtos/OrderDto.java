package com.quikbite.app.order.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quikbite.app.auth_users.dtos.UserDto;
import com.quikbite.app.enums.OrderStatus;
import com.quikbite.app.enums.PaymentStatus;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {
    private Long id;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private UserDto user;
    private List<OrderItemDto> orderItems;
}
