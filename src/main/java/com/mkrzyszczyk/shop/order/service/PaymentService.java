package com.mkrzyszczyk.shop.order.service;

import com.mkrzyszczyk.shop.order.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> getPayments();
}
