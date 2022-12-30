package com.mkrzyszczyk.shop.common.mapping;

import com.mkrzyszczyk.shop.common.dto.ProductListDto;
import com.mkrzyszczyk.shop.common.model.Product;

public class ProductListMapping {

  public ProductListDto mapToProductListDto(Product product) {
    return ProductListDto.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .currency(product.getCurrency())
        .image(product.getImage())
        .slug(product.getSlug())
        .build();
  }

}
