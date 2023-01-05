package com.mkrzyszczyk.shop.cart.controller.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {

  private Long id;
  private String name;
  private BigDecimal price;
  private String currency;
  private String image;
  private String slug;
}
