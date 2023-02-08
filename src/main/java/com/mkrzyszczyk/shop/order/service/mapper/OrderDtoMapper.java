package com.mkrzyszczyk.shop.order.service.mapper;

import com.mkrzyszczyk.shop.order.model.Order;
import com.mkrzyszczyk.shop.order.model.dto.OrderListDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDtoMapper {

    public static List<OrderListDto> mapToOrderListDto(List<Order> orders) {
        return orders.stream().map(order -> new OrderListDto(order.getId(),
                        order.getPlacementDate(),
                        order.getOrderStatus().getValue(),
                        order.getGrossValue()))
                .collect(Collectors.toList());
    }
}
