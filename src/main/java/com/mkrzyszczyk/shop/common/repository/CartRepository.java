package com.mkrzyszczyk.shop.common.repository;

import com.mkrzyszczyk.shop.common.model.Cart;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {

  List<Cart> findByCreatedLessThan(LocalDateTime minusDays);

  @Query("delete from Cart c where c.id in (:ids)")
  @Modifying
  void deleteAllByCartIdIn(List<Long> ids);

  @Query("delete from Cart c where c.id =:id")
  @Modifying
  void deleteByCartId(Long id);
}
