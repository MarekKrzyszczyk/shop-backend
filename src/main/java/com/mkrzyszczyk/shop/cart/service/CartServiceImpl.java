package com.mkrzyszczyk.shop.cart.service;

import com.mkrzyszczyk.shop.cart.controller.dto.CartSummaryDto;
import com.mkrzyszczyk.shop.cart.controller.mapper.CartMapper;
import com.mkrzyszczyk.shop.cart.model.Cart;
import com.mkrzyszczyk.shop.cart.model.CartItem;
import com.mkrzyszczyk.shop.cart.model.dto.CartProductDto;
import com.mkrzyszczyk.shop.cart.repository.CartRepository;
import com.mkrzyszczyk.shop.common.model.Product;
import com.mkrzyszczyk.shop.common.repository.ProductRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final CartMapper cartMapper;

  @Override
  public CartSummaryDto getCart(Long id) {
    return cartMapper.mapToCartSummary(cartRepository.findById(id).orElseThrow());
  }

  @Override
  @Transactional
  public CartSummaryDto addProductToCart(Long id, CartProductDto cartProductDto) {
    Cart cart = getInitializedCart(id);
    cart.addProduct(CartItem.builder()
            .quantity(cartProductDto.quantity())
            .product(getProduct(cartProductDto.productId()))
            .cartId(cart.getId())
        .build());

    return cartMapper.mapToCartSummary(cart);
  }

  private Cart getInitializedCart(Long id) {
    if (id == null || id <= 0) {
      return cartRepository.save(Cart.builder()
              .created(LocalDateTime.now())
          .build());
    }
    return cartRepository.findById(id).orElseThrow();
  }

  private Product getProduct(Long id) {
    return productRepository.findById(id).orElseThrow();
  }
}
