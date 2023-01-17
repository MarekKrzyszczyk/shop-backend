package com.mkrzyszczyk.shop.admin.order.controller;

import com.mkrzyszczyk.shop.admin.order.model.dto.AdminOrderStats;
import com.mkrzyszczyk.shop.admin.order.service.AdminOrderStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/orders/stats")
public class AdminOrderStatsController {

    private final AdminOrderStatsService adminOrderStatsService;

    @GetMapping
    public ResponseEntity<AdminOrderStats> getOrderStats() {
        return ResponseEntity.ok(adminOrderStatsService.getOrderStats());
    }
}
