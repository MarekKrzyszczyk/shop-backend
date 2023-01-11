package com.mkrzyszczyk.shop.cart.repository;

import com.mkrzyszczyk.shop.cart.model.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

  Long countByCartId(Long id);

  @Query("delete from CartItem ci where ci.cartId in (:ids)")
  @Modifying
  void deleteAllByCartIdIn(List<Long> ids);
}
