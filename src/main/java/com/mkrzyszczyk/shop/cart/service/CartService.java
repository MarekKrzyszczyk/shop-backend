package com.mkrzyszczyk.shop.cart.service;

import com.mkrzyszczyk.shop.cart.controller.dto.CartSummaryDto;
import com.mkrzyszczyk.shop.cart.model.Cart;
import com.mkrzyszczyk.shop.cart.model.dto.CartProductDto;

public interface CartService {

  CartSummaryDto getCart(Long id);

  CartSummaryDto addProductToCart(Long id, CartProductDto cartProductDto);
}
