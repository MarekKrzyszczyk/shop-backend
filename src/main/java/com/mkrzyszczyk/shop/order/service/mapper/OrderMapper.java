package com.mkrzyszczyk.shop.order.service.mapper;

import com.mkrzyszczyk.shop.common.model.Cart;
import com.mkrzyszczyk.shop.common.model.CartItem;
import com.mkrzyszczyk.shop.order.model.*;
import com.mkrzyszczyk.shop.order.model.dto.OrderDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderSummary;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {

    public static Order createNewOrder(OrderDto orderDto, Cart cart, Shipment shipment, Payment payment) {
        return Order.builder()
                .firstname(orderDto.getFirstname())
                .lastname(orderDto.getLastname())
                .street(orderDto.getStreet())
                .zipcode(orderDto.getZipcode())
                .city(orderDto.getCity())
                .email(orderDto.getEmail())
                .phone(orderDto.getPhone())
                .placementDate(LocalDateTime.now())
                .orderStatus(OrderStatus.NEW)
                .grossValue(calculateGrossValue(cart.getItems(), shipment))
                .payment(payment)
                .build();
    }

    private static BigDecimal calculateGrossValue(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
    }

    public static OrderSummary createOrderSummary(Payment payment, Order order) {
        return OrderSummary.builder()
                .id(order.getId())
                .placementDate(order.getPlacementDate())
                .status(order.getOrderStatus().toString())
                .grossValue(order.getGrossValue())
                .payment(payment)
                .build();
    }

    public static OrderRow mapToOrderRow(Long orderId, Shipment shipment) {
        return OrderRow.builder()
                .quantity(1)
                .price(shipment.getPrice())
                .orderId(orderId)
                .shipmentId(shipment.getId())
                .build();
    }

    public static OrderRow mapToOrderRowWithQuantity(Long orderId, CartItem cartItem) {
        return OrderRow.builder()
                .productId(cartItem.getProduct().getId())
                .price(cartItem.getProduct().getPrice())
                .quantity(cartItem.getQuantity())
                .orderId(orderId)
                .build();
    }
}
