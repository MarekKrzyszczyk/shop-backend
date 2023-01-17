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
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

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

        TreeMap<Integer, AdminOrderStatsValue> result =
                IntStream.rangeClosed(from.getDayOfMonth(), to.getDayOfMonth())
                        .boxed()
                        .map(i -> aggregateValues(i, adminOrders))
                        .collect(toMap(
                                AdminOrderStatsValue::day,
                                value -> value,
                                (t, t2) -> {
                                    throw new IllegalArgumentException();
                                    },
                                TreeMap::new));

        List<Long> ordersList = result.values().stream().map(o -> o.ordersNumber).toList();
        List<BigDecimal> salesList = result.values().stream().map(o -> o.salesAmount).toList();

        return AdminOrderStats.builder()
                .labels(result.keySet().stream().toList())
                .orders(ordersList)
                .sales(salesList)
                .ordersNumber(ordersList.stream().reduce(Long::sum).orElse(0L))
                .salesAmount(salesList.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO))
                .build();
    }

    private AdminOrderStatsValue aggregateValues(Integer i, List<AdminOrder> adminOrders) {
        return adminOrders.stream()
                .filter(adminOrder ->
                        adminOrder.getPlacementDate().getDayOfMonth() == i)
                .map(AdminOrder::getGrossValue)
                .reduce(new AdminOrderStatsValue(i, BigDecimal.ZERO, 0L),
                        (AdminOrderStatsValue o, BigDecimal v) -> new
                                AdminOrderStatsValue(i, o.salesAmount().add(v), o.ordersNumber() + 1),
                        (o, o2) -> null);
    }

    private record AdminOrderStatsValue(Integer day, BigDecimal salesAmount, Long ordersNumber) {
    }
}
