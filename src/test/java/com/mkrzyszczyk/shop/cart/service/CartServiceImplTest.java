package com.mkrzyszczyk.shop.cart.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.mkrzyszczyk.shop.cart.controller.dto.CartSummaryDto;
import com.mkrzyszczyk.shop.cart.controller.mapper.CartMapper;
import com.mkrzyszczyk.shop.cart.model.Cart;
import com.mkrzyszczyk.shop.cart.model.dto.CartProductDto;
import com.mkrzyszczyk.shop.cart.repository.CartRepository;
import com.mkrzyszczyk.shop.common.model.Product;
import com.mkrzyszczyk.shop.common.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

  @Mock
  private CartRepository cartRepository;
  @Mock
  private ProductRepository productRepository;
  @Spy
  private CartMapper cartMapper;
  @InjectMocks
  private CartServiceImpl cartService;

  @Test
  void shouldAddProductToCartWhenCartIdNotExists() {
    //given
    Long cartId = 0L;
    CartProductDto cartProductDto = new CartProductDto(1L, 1);
    when(productRepository.findById(1L)).thenReturn(Optional.of(Product.builder().id(1L).price(BigDecimal.ONE).build()));
    when(cartRepository.save(any())).thenReturn(Cart.builder().id(1L).build());
    //when
    CartSummaryDto result = cartService.addProductToCart(cartId, cartProductDto);
    //then
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(1L);
  }

  @Test
  void shouldAddProductToCartWhenCartIdExists() {
    //given
    Long cartId = 1L;
    CartProductDto cartProductDto = new CartProductDto(1L, 1);
    when(productRepository.findById(1L)).thenReturn(Optional.of(Product.builder().id(1L).price(BigDecimal.ONE).build()));
    when(cartRepository.findById(1L)).thenReturn(Optional.of(Cart.builder().id(1L).build()));
    //when
    CartSummaryDto result = cartService.addProductToCart(cartId, cartProductDto);
    //then
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(1L);
  }
}