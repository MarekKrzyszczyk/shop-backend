package com.mkrzyszczyk.shop.product.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

  private String name;
  private String category;
  private String description;
  private BigDecimal price;
  private String currency;

}
