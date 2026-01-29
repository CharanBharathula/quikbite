package com.quikbite.app.payment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.quikbite.app.auth_users.model.User;
import com.quikbite.app.enums.PaymentGateway;
import com.quikbite.app.enums.PaymentStatus;
import com.quikbite.app.order.model.Order;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentGateway gateway;

    private String failureReason;

    private LocalDateTime paymenDate;

}
