package com.mkrzyszczyk.shop.cart.repository;

import com.mkrzyszczyk.shop.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
