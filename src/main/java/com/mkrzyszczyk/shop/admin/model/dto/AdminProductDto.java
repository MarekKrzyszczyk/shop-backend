package com.mkrzyszczyk.shop.admin.model.dto;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class AdminProductDto {

  private String name;
  private String category;
  private String description;
  private BigDecimal price;
  private String currency;
}
