package com.mkrzyszczyk.shop.cart.controller;

import com.mkrzyszczyk.shop.cart.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartItems")
@RequiredArgsConstructor
public class CartItemController {

  private final CartItemService cartItemService;

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
    cartItemService.deleteCartItem(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/count/{id}")
  public Long countItemInCart(@PathVariable Long id) {
    return cartItemService.countItemsInCart(id);
  }
}