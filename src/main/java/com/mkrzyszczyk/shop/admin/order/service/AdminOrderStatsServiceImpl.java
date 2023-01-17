package com.mkrzyszczyk.shop.admin.order.service;

import com.mkrzyszczyk.shop.admin.order.model.AdminOrder;
import com.mkrzyszczyk.shop.admin.order.model.AdminOrderStatus;
import com.mkrzyszczyk.shop.admin.order.model.dto.AdminOrderStats;
import com.mkrzyszczyk.shop.admin.order.repository.AdminOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class AdminOrderStatsServiceImpl implements AdminOrderStatsService {

    private final AdminOrderRepository adminOrderRepository;

    @Override
    public AdminOrderStats getOrderStats() {
        LocalDateTime from = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime to = LocalDateTime.now();
        List<AdminOrder> adminOrders = adminOrderRepository.findAllByPlacementDateIsBetweenAndOrderStatus(
                from, to, AdminOrderStatus.COMPLETED);

        TreeMap<Integer, AdminOrderStatsValue> result = new TreeMap<>();
        for (int i = from.getDayOfMonth(); i <= to.getDayOfMonth(); i++) {
            result.put(i, aggregateValues(i, adminOrders));
        }

        return AdminOrderStats.builder()
                .labels(result.keySet().stream().toList())
                .orders(result.values().stream().map(o -> o.ordersNumber).toList())
                .sales(result.values().stream().map(o -> o.salesAmount).toList())
                .build();
    }

    private AdminOrderStatsValue aggregateValues(int i, List<AdminOrder> adminOrders) {
        BigDecimal totalValue = BigDecimal.ZERO;
        Long ordersNumber = 0L;
        for (AdminOrder adminOrder : adminOrders) {
            if (i == adminOrder.getPlacementDate().getDayOfMonth()) {
                totalValue = totalValue.add(adminOrder.getGrossValue());
                ordersNumber++;
            }
        }
        return new AdminOrderStatsValue(totalValue, ordersNumber);
    }

    private record AdminOrderStatsValue(BigDecimal salesAmount, Long ordersNumber) {
    }
}
