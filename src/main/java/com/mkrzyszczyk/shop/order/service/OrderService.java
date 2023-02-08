package com.mkrzyszczyk.shop.order.service;

import com.mkrzyszczyk.shop.order.model.Order;
import com.mkrzyszczyk.shop.order.model.dto.OrderDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderListDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderSummary;

import java.util.List;

public interface OrderService {

  OrderSummary placeOrder(OrderDto orderDto, Long userId);

    List<OrderListDto> getOrderForCustomer(Long userId);
}
