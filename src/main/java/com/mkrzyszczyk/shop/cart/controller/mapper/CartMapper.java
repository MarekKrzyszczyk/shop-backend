package com.mkrzyszczyk.shop.cart.controller.mapper;

import com.mkrzyszczyk.shop.cart.controller.dto.CartSummaryDto;
import com.mkrzyszczyk.shop.cart.controller.dto.CartSummaryItemDto;
import com.mkrzyszczyk.shop.cart.controller.dto.ProductDto;
import com.mkrzyszczyk.shop.cart.controller.dto.SummaryDto;
import com.mkrzyszczyk.shop.cart.model.Cart;
import com.mkrzyszczyk.shop.cart.model.CartItem;
import com.mkrzyszczyk.shop.common.model.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

  public CartSummaryDto mapToCartSummary(Cart cart) {
   return CartSummaryDto.builder()
       .id(cart.getId())
       .items(mapCartItems(cart.getItems()))
       .summary(mapToSummary(cart.getItems()))
       .build();
  }

  private List<CartSummaryItemDto> mapCartItems(List<CartItem> items) {
    return items.stream().map(this::mapToCartItem).toList();
  }

  private CartSummaryItemDto mapToCartItem(CartItem item) {
    return CartSummaryItemDto.builder()
        .id(item.getId())
        .quantity(item.getQuantity())
        .productDto(mapToProductDto(item.getProduct()))
        .lineValue(calculateLineValue(item))
        .build();
  }

  private ProductDto mapToProductDto(Product product) {
    return ProductDto.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .currency(product.getCurrency())
        .image(product.getImage())
        .slug(product.getSlug())
        .build();
  }

  private BigDecimal calculateLineValue(CartItem item) {
    return BigDecimal.valueOf(item.getQuantity()).multiply(item.getProduct().getPrice());
  }

  private SummaryDto mapToSummary(List<CartItem> items) {
    return SummaryDto.builder()
        .grossValue(sumValues(items))
        .build();
  }

  private BigDecimal sumValues(List<CartItem> items) {
    return items.stream()
        .map(this::calculateLineValue)
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO);
  }
}
