package com.mkrzyszczyk.shop.order.service;

import com.mkrzyszczyk.shop.common.model.Cart;
import com.mkrzyszczyk.shop.common.model.CartItem;
import com.mkrzyszczyk.shop.common.repository.CartItemRepository;
import com.mkrzyszczyk.shop.common.repository.CartRepository;
import com.mkrzyszczyk.shop.order.model.*;
import com.mkrzyszczyk.shop.order.model.dto.OrderDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderSummary;
import com.mkrzyszczyk.shop.order.repository.OrderRepository;
import com.mkrzyszczyk.shop.order.repository.OrderRowRepository;
import com.mkrzyszczyk.shop.order.repository.PaymentRepository;
import com.mkrzyszczyk.shop.order.repository.ShipmentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto) {
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId()).orElseThrow();
        Order order = Order.builder()
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
        Order newOrder = orderRepository.save(order);
        saveOrderRows(cart, newOrder.getId(), shipment);
        cartItemRepository.deleteByCartId(cart.getId());
        cartRepository.deleteByCartId(cart.getId());

        return OrderSummary.builder()
                .id(newOrder.getId())
                .placementDate(newOrder.getPlacementDate())
                .status(newOrder.getOrderStatus().toString())
                .grossValue(newOrder.getGrossValue())
                .payment(payment)
                .build();
    }

    private BigDecimal calculateGrossValue(List<CartItem> items, Shipment shipment) {
        return items.stream()
                .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(shipment.getPrice());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductsRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveProductsRows(Cart cart, Long orderId) {
        cart.getItems().stream().map(cartItem -> OrderRow.builder()
                        .productId(cartItem.getProduct().getId())
                        .price(cartItem.getProduct().getPrice())
                        .quantity(cartItem.getQuantity())
                        .orderId(orderId)
                        .build())
                .forEach(orderRowRepository::save);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(OrderRow.builder()
                .quantity(1)
                .price(shipment.getPrice())
                .orderId(orderId)
                .shipmentId(shipment.getId())
                .build());
    }
}