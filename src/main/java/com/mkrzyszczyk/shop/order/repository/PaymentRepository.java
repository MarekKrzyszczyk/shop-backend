package com.mkrzyszczyk.shop.order.repository;

import com.mkrzyszczyk.shop.order.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
