package com.mkrzyszczyk.shop.order.service;

import com.mkrzyszczyk.shop.common.mail.EmailSimpleService;
import com.mkrzyszczyk.shop.common.model.Cart;
import com.mkrzyszczyk.shop.common.model.CartItem;
import com.mkrzyszczyk.shop.common.repository.CartItemRepository;
import com.mkrzyszczyk.shop.common.repository.CartRepository;
import com.mkrzyszczyk.shop.order.model.Order;
import com.mkrzyszczyk.shop.order.model.OrderRow;
import com.mkrzyszczyk.shop.order.model.OrderStatus;
import com.mkrzyszczyk.shop.order.model.Shipment;
import com.mkrzyszczyk.shop.order.model.dto.OrderDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderSummary;
import com.mkrzyszczyk.shop.order.repository.OrderRepository;
import com.mkrzyszczyk.shop.order.repository.OrderRowRepository;
import com.mkrzyszczyk.shop.order.repository.ShipmentRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
  private final EmailSimpleService emailSimpleService;

  @Override
  @Transactional
  public OrderSummary placeOrder(OrderDto orderDto) {
    Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
    Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId()).orElseThrow();
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
        .build();
    Order newOrder = orderRepository.save(order);
    saveOrderRows(cart, newOrder.getId(), shipment);
    cartItemRepository.deleteByCartId(cart.getId());
    cartRepository.deleteByCartId(cart.getId());
    emailSimpleService.send(order.getEmail(), "Your order has been placed", createEmailMessage(order));

    return OrderSummary.builder()
        .id(newOrder.getId())
        .placementDate(newOrder.getPlacementDate())
        .status(newOrder.getOrderStatus().toString())
        .grossValue(newOrder.getGrossValue())
        .build();
  }

  private String createEmailMessage(Order order) {
    return "Your order nr: " + order.getId() +
        "\nPlacement date: " + order.getPlacementDate().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm")) +
        "\nAmount: " + order.getGrossValue() + " PLN" +
        "\n\n" +
        "\nPayment: " + order.getPayment().getName() + (order.getPayment().getNote() != null ?
        "\n" + order.getPayment().getNote() : "") +
        "\n\nThank you for your order!";
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