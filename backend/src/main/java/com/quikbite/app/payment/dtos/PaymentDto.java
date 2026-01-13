package com.quikbite.app.payment.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quikbite.app.auth_users.dtos.UserDto;
import com.quikbite.app.enums.PaymentGateway;
import com.quikbite.app.enums.PaymentStatus;
import com.quikbite.app.order.dtos.OrderDto;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDto {
    // generate Dto fields below based on Payment entity Payment.java

    private Long id;
    private Long userId;
    private Long orderId;
    private String amount;
    private PaymentStatus status;
    private String transactionId;
    private PaymentGateway gateway;
    private String failureReason;
    private LocalDateTime paymentDate;

    private boolean success;
    private OrderDto order;
    private UserDto user;

}
