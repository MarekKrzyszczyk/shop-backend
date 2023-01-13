package com.mkrzyszczyk.shop.order.service;

import com.mkrzyszczyk.shop.order.model.Payment;
import com.mkrzyszczyk.shop.order.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
}
