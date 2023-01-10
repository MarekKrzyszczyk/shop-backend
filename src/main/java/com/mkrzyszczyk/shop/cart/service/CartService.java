package com.mkrzyszczyk.shop.cart.service;

import com.mkrzyszczyk.shop.cart.controller.dto.CartSummaryDto;
import com.mkrzyszczyk.shop.cart.model.dto.CartProductDto;
import java.util.List;

public interface CartService {

  CartSummaryDto getCart(Long id);

  CartSummaryDto addProductToCart(Long id, CartProductDto cartProductDto);

  CartSummaryDto updateCart(Long id, List<CartProductDto> cartProductsDto);
}
