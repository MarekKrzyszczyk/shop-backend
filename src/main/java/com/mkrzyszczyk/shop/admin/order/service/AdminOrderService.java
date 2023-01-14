package com.mkrzyszczyk.shop.admin.order.service;

import com.mkrzyszczyk.shop.admin.order.model.AdminOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AdminOrderService {

    Page<AdminOrder> getOrders(Pageable pageable);

    AdminOrder getOrder(Long id);

    void patchOrder(Long id, Map<String, String> values);
}
