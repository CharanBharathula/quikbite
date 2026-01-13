package com.quikbite.app.cart.dtos;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quikbite.app.menu.dtos.MenuDto;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields during serialization
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown fields during deserialization

public class CartItemDto {
    private Long id;
    private MenuDto menu;
    private int quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal subTotal;
}