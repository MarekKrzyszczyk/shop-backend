package com.mkrzyszczyk.shop.cart.service;

import com.mkrzyszczyk.shop.cart.controller.dto.CartSummaryDto;
import com.mkrzyszczyk.shop.cart.controller.mapper.CartMapper;
import com.mkrzyszczyk.shop.cart.model.dto.CartProductDto;
import com.mkrzyszczyk.shop.common.model.Cart;
import com.mkrzyszczyk.shop.common.model.CartItem;
import com.mkrzyszczyk.shop.common.model.Product;
import com.mkrzyszczyk.shop.common.repository.CartRepository;
import com.mkrzyszczyk.shop.common.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
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

  @Override
  @Transactional
  public CartSummaryDto updateCart(Long id, List<CartProductDto> cartProductsDto)
  {
    Cart cart = cartRepository.findById(id).orElseThrow();
    cart.getItems().forEach(item -> {
      cartProductsDto.stream()
          .filter(cartProductDto -> item.getProduct().getId().equals(cartProductDto.productId()))
          .findFirst()
          .ifPresent(cartProductDto -> item.setQuantity((cartProductDto.quantity())));
    });
    return cartMapper.mapToCartSummary(cart);
  }

  private Cart getInitializedCart(Long id) {
    if (id == null || id <= 0) {
      return saveNewCart();
    }
    return cartRepository.findById(id).orElseGet(this::saveNewCart);
  }

  private Product getProduct(Long id) {
    return productRepository.findById(id).orElseThrow();
  }

  private Cart saveNewCart() {
    return cartRepository.save(Cart.builder().created(LocalDateTime.now()).build());
  }
}
