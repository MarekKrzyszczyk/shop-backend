package com.mkrzyszczyk.shop.order.repository;

import com.mkrzyszczyk.shop.order.model.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {

}
