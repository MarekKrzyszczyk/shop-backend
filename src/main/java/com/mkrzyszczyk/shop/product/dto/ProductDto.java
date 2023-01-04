package com.mkrzyszczyk.shop.product.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {
  private Long id;
  private String name;
  private Long categoryId;
  private String description;
  private String fullDescription;
  private BigDecimal price;
  private String currency;
  private String image;
  private String slug;
  private List<ReviewDto> reviews;
}
