package com.mkrzyszczyk.shop.cart.service;

public interface CartItemService {

  void deleteCartItem(Long id);

  Long countItemsInCart(Long id);
}
