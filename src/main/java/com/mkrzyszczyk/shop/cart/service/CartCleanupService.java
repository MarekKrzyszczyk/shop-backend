package com.mkrzyszczyk.shop.cart.service;

import com.mkrzyszczyk.shop.common.model.Cart;
import com.mkrzyszczyk.shop.common.repository.CartItemRepository;
import com.mkrzyszczyk.shop.common.repository.CartRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartCleanupService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;

  @Transactional
  @Scheduled(cron = "${app.cart.cleanup.expression}")
  public void cleanupOldCarts() {
    List<Cart> carts = cartRepository.findByCreatedLessThan(LocalDateTime.now().minusDays(3));
    List<Long> ids = carts.stream()
        .map(Cart::getId)
        .toList();
    log.info("Old carts: " + carts.size());

    if (!ids.isEmpty()) {
      cartItemRepository.deleteAllByCartIdIn(ids);
      cartRepository.deleteAllByCartIdIn(ids);
    }
  }
}
