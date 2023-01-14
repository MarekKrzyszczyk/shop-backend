package com.mkrzyszczyk.shop.admin.order.repository;

import com.mkrzyszczyk.shop.admin.order.model.AdminOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {
}
