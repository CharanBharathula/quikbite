package com.quikbite.app.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quikbite.app.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
