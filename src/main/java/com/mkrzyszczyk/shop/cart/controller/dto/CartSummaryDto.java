package com.mkrzyszczyk.shop.cart.controller.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartSummaryDto {

  private Long id;
  private List<CartSummaryItemDto> items;
  private SummaryDto summary;
}
