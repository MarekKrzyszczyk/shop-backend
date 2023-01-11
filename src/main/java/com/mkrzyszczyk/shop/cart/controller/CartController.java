package com.mkrzyszczyk.shop.cart.controller;

import com.mkrzyszczyk.shop.cart.controller.dto.CartSummaryDto;
import com.mkrzyszczyk.shop.cart.model.dto.CartProductDto;
import com.mkrzyszczyk.shop.cart.service.CartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @GetMapping("/{id}")
  public ResponseEntity<CartSummaryDto> getCart(@PathVariable Long id) {
    return ResponseEntity.ok(cartService.getCart(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CartSummaryDto> addProductTiCart(@PathVariable Long id, @RequestBody CartProductDto cartProductDto) {
    return ResponseEntity.ok(cartService.addProductToCart(id, cartProductDto));
  }

  @PutMapping("/{id}/update")
  public ResponseEntity<CartSummaryDto> updateCart(@PathVariable Long id,
      @RequestBody List<CartProductDto> cartProductsDto) {
    return ResponseEntity.ok(cartService.updateCart(id, cartProductsDto));
  }
}
