package com.mkrzyszczyk.shop.admin.order.repository;

import com.mkrzyszczyk.shop.admin.order.model.AdminOrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminOrderLogRepository extends JpaRepository<AdminOrderLog, Long> {
}