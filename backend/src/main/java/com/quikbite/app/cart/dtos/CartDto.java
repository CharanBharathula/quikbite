package com.quikbite.app.cart.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields during serialization
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields during deserialization
public class CartDto {
    private Long id;
    private List<CartItemDto> cartItems;
    private Long menuId;
    private int quantity;
    private BigDecimal totalAmount;
}
