package com.mkrzyszczyk.shop.cart.repository;

import com.mkrzyszczyk.shop.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  Long countByCartId(Long id);
}
