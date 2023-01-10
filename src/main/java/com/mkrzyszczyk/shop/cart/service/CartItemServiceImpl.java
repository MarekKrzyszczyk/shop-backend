package com.mkrzyszczyk.shop.cart.service;

import com.mkrzyszczyk.shop.cart.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

  private final CartItemRepository cartItemRepository;

  @Override
  public void deleteCartItem(Long id) {
    cartItemRepository.deleteById(id);
  }
}
