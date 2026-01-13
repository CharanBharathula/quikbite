package com.quikbite.app.order.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quikbite.app.menu.dtos.MenuDto;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class OrderItemDto {
    private Long id;
    private int quantity;
    private Long menuId;
    private MenuDto menu;
    private BigDecimal pricePerUnit;
    private BigDecimal subTotal;
}
