package com.mkrzyszczyk.shop.order.service;

import com.mkrzyszczyk.shop.order.model.dto.OrderDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderSummary;

public interface OrderService {

  OrderSummary placeOrder(OrderDto orderDto);
}
