package com.mkrzyszczyk.shop.order.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.mkrzyszczyk.shop.order.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderSummary {
  private Long id;
  private LocalDateTime placementDate;
  private String status;
  private BigDecimal grossValue;
  private Payment payment;
}
