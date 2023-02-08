package com.mkrzyszczyk.shop.order.controller;

import com.mkrzyszczyk.shop.order.model.dto.InitOrder;
import com.mkrzyszczyk.shop.order.model.dto.OrderDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderListDto;
import com.mkrzyszczyk.shop.order.model.dto.OrderSummary;
import com.mkrzyszczyk.shop.order.service.OrderService;
import com.mkrzyszczyk.shop.order.service.PaymentService;
import com.mkrzyszczyk.shop.order.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;
  private final ShipmentService shipmentService;
  private final PaymentService paymentService;

  @PostMapping
  public ResponseEntity<OrderSummary> placeOrder(@RequestBody @Valid OrderDto orderDto, @AuthenticationPrincipal Long userId) {
    return new ResponseEntity<>(orderService.placeOrder(orderDto, userId), HttpStatus.CREATED);
  }

  @GetMapping("/initData")
  public ResponseEntity<InitOrder> initData() {
    InitOrder initOrder = InitOrder.builder()
            .shipments(shipmentService.getShipments())
            .payments(paymentService.getPayments())
            .build();
    return ResponseEntity.ok(initOrder);
  }

  @GetMapping
  public List<OrderListDto> getOrders(@AuthenticationPrincipal Long userId) {
    if (userId == null) {
      throw new IllegalArgumentException("User is missing");
    }
    return orderService.getOrderForCustomer(userId);
  }
}
