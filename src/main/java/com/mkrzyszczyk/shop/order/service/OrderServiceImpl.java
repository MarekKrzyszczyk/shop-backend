package com.mkrzyszczyk.shop.order.service;

import com.mkrzyszczyk.shop.common.model.Cart;
import com.mkrzyszczyk.shop.common.model.CartItem;
import com.mkrzyszczyk.shop.common.repository.CartItemRepository;
import com.mkrzyszczyk.shop.common.repository.CartRepository;
import com.mkrzyszczyk.shop.order.model.Order;
import com.mkrzyszczyk.shop.order.model.OrderRow;
import com.mkrzyszczyk.shop.order.model.OrderStatus;
import com.mkrzyszczyk.shop.order.model.dto.OrderDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderSummary;
import com.mkrzyszczyk.shop.order.repository.OrderRepository;
import com.mkrzyszczyk.shop.order.repository.OrderRowRepository;
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

  @Override
  @Transactional
  public OrderSummary placeOrder(OrderDto orderDto) {
    Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow();
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
        .grossValue(calculateGrossValue(cart.getItems()))
        .build();
    Order newOrder = orderRepository.save(order);
    saveOrderRows(cart, newOrder.getId());
    cartItemRepository.deleteByCartId(cart.getId());
    cartRepository.deleteByCartId(cart.getId());

    return OrderSummary.builder()
        .id(newOrder.getId())
        .placementDate(newOrder.getPlacementDate())
        .status(newOrder.getOrderStatus().toString())
        .grossValue(newOrder.getGrossValue())
        .build();
  }

  private BigDecimal calculateGrossValue(List<CartItem> items) {
    return items.stream()
        .map(cartItem -> cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO);
  }

  private void saveOrderRows(Cart cart, Long orderId) {
    cart.getItems().stream().map(cartItem -> OrderRow.builder()
            .productId(cartItem.getProduct().getId())
            .quantity(cartItem.getQuantity())
            .price(cartItem.getProduct().getPrice())
            .orderId(orderId)
            .build())
        .forEach(orderRowRepository::save);
  }
}
