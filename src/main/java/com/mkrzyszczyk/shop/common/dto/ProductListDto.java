package com.mkrzyszczyk.shop.common.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductListDto {

  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
  private String currency;
  private String image;
  private String slug;
}
