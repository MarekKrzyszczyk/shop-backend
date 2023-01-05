package com.mkrzyszczyk.shop.cart.controller.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartSummaryItemDto {

  private Long id;
  private int quantity;
  private ProductDto productDto;
  private BigDecimal lineValue;
}
